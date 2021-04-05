<?php

namespace App\Controller;

use App\Entity\Aliment;
use App\Entity\Proportion;
use App\Form\ProportionType;
use App\Repository\AlimentRepository;
use App\Repository\ProportionRepository;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class ProportionController extends AbstractController
{
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
       // $proportions = $repoP->findbetwen2date($startDate,$endDate);
       // if ($proportions ==null)
        {
           // $proportion=new Proportion();
        }
       // $form =$this->createForm(ProportionType::class,$proportion);
       // dump($proportions);
        $aliment=$paginator->paginate(
            $donnees,
            /* query NOT result */
            $request->query->getInt('page', 1), /*numero de page en cours 1 par défaut*/
            7 /*limit per page*/
        );
        return $this->render("front/proportions/foodIntake.html.twig",
            ["aliment"=>$aliment]

        );
    }
    public function gestionnourriture(Request $request) :Response
    {
       $proportion=new Proportion();
        $form =$this->createForm(ProportionType::class,$proportion);
        //  $form->add("Ajouter",SubmitType::class);
        $form->handleRequest($request);//gere requette envoyer par l'utlisateur


        if($form->isSubmitted() && $form->isValid()){
            $proportion->setDate(new \DateTime());

            $proportion->setNutritionniste($this->getUser());
                $em=$this->getDoctrine()->getManager();

                $em->persist($proportion);
                $em->flush();
                return $this->redirectToRoute('docadmin_afficherAliment');
            }
        return $this->render("back/aliment/ajouterAliment.html.twig",
            [  'form'=> $form->createView(), ]);

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


