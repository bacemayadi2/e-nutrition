<?php

namespace App\Controller;

use App\Entity\Aliment;
use App\Entity\Proportion;
use App\Form\PlatType;
use App\Form\ProportionType;
use App\Repository\AlimentRepository;
use App\Repository\ProportionRepository;
use Knp\Component\Pager\PaginatorInterface;
use phpDocumentor\Reflection\Types\Collection;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class ProportionController extends AbstractController
{

    /**
     * @param Request $request
     * @param $id
     * @param AlimentRepository $repo
     * @return \Symfony\Component\HttpFoundation\RedirectResponse
     * @Route ("/ajouterproportion/{id}/{quantite}",name="ajouterproportion")
     */
    public function ajoute(Request $request,$id,$quantite,AlimentRepository $repo)
    {
        $aliment=$repo->find($id);
        $proportion =new Proportion();
        $proportion->setPoid($quantite);
        $proportion->setPatient($this->getUser());
        $proportion->setAliment($aliment);
        $proportion->setDate(new \DateTime());
        $proportion->calculatenutritionvalue();
        $em=$this->getDoctrine()->getManager();
        $em->persist($proportion);
        $em->flush();
        return $this->redirectToRoute('Proportions');

    }
    /**
     * @Route("/proportion", name="proportion")
     */
    public function index(): Response
    {
        return $this->render('front/proportions/foodIntake.html.twig', [
            'controller_name' => 'ProportionController',
        ]);
    }

    /**
     * @param AlimentRepository $repo
     * @param PaginatorInterface $paginator
     * @param Request $request
     * @return Response
     * @Route ("Proportions",name="Proportions")

     */
    public function afficheraliment(AlimentRepository $repo,PaginatorInterface $paginator,Request $request,ProportionRepository  $repoP)
    {
        $donnees=$repo->findAll();
        $endDate = (new \DateTime());
        $startDate = (clone $endDate)->modify('- 1 day ');
        $oldproportions=$repoP->findbetwen2date($startDate,$endDate);

        $proportion[]=null;


        $aliment=$paginator->paginate(
            $donnees,
            /* query NOT result */
            $request->query->getInt('page', 1), /*numero de page en cours 1 par défaut*/
            7 /*limit per page*/
        );
        $forms[]=null;
        $i=0;
//        foreach ($aliment as $a )
//        {
//            $proportion[$i]=new Proportion();
//            $proportion[$i]->setPoid($a->getPoid());
//            $forms[$i] =$this->createForm(ProportionType::class, $proportion[$i]);

//            $forms[$i] = $this->get('form.factory')->createNamed(
//                'form'.$i, // unique form name
//                ProportionType::class,
//                $proportion[$i]
//            );
//            $forms[$i]->handleRequest($request);


//            if ($forms[$i]->isSubmitted() && $forms[$i]->isValid()) {
//                // do what you want with $forms[$article->getId()]->getData()
//                // ...
//            }

//            dump($i);
//            $i=$i+1;
//        }
//        $f =array(
//            'for' => array_map(function ($form) {
//                return $form->createView();
//            }, $forms), );
        $totalCalories=0;
        $totalLipides=0;
        $totalProteins=0;
        $totalGlucides=0;
        dump ($oldproportions);
        foreach ($oldproportions as $p)
        {
            $p->calculatenutritionvalue();
            $totalCalories+=$p->getCalorie();
            $totalLipides+=$p->getLipides();
            $totalProteins+=$p->getProteines();
            $totalGlucides+=$p->getGlucides();
        }


        return $this->render("front/proportions/foodIntake.html.twig",
            ["aliment"=>$aliment,"proportions"=>$oldproportions,"tc"=>$totalCalories,"tl"=>$totalLipides,"tp"=>$totalProteins,"tg"=>$totalGlucides]

        );
    }
    /**
     * @param $id
     * @Route ("/supprimerproportion/{id}",name="supprimerproportion")
     */
    function delete(ProportionRepository $repo ,$id)
    {
        $em=$this->getDoctrine()->getManager()  ;
        $proportion=$repo->find($id);
        $em->remove($proportion);
        $em->flush();
        return $this->redirectToRoute('Proportions');
    }


    /**
     * @param AlimentRepository $repository
     * @param Request $request
     * @param PaginatorInterface $paginator
     * @param NormalizerInterface $normalizer
     * @return Response
     * @Route ("rechercheAliment",name="rechercheAliment")
     * @throws \Symfony\Component\Serializer\Exception\ExceptionInterface
     */
    public function Recherche(AlimentRepository $repository,Request $request,PaginatorInterface $paginator,NormalizerInterface $normalizer)
    {


//        $aliment=$paginator->paginate(
//            $donnees,
//            /* query NOT result */
//            $request->query->getInt('page', 1), /*numero de page en cours 1 par défaut*/
//            7/*limit per page*/
//        );
//        return $this->render("back/aliment/afficherAliment.html.twig",
//            ["aliment"=>$aliment] );

        $repository = $this->getDoctrine()->getRepository(Aliment::class);
        $name=$request->get('searchValue');
        $donnees=$repository->findalimentbyname($name);

        $aliment=$repository->findalimentbyname($name);
//        $aliment=$paginator->paginate(
//            $donnees,
//            /* query NOT result */
//            $request->query->getInt('page', 1), /*numero de page en cours 1 par défaut*/
//            7/*limit per page*/
//        );
        $jsonContent = $normalizer->normalize($aliment, 'json',['groups'=>'aliments']);
        $retour =json_encode($jsonContent);
        return new Response($retour);
    }
}


