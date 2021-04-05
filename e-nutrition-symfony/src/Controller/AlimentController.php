<?php

namespace App\Controller;

use App\Entity\Aliment;
use App\Entity\CategorieAliment;
use App\Form\AlimentType;
use App\Repository\AlimentRepository;
use App\Repository\CategorieAlimentRepository;
use Knp\Component\Pager\PaginatorInterface;
use phpDocumentor\Reflection\Types\String_;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;


class AlimentController extends AbstractController
{
    /**
     * @Route("/docadmin/back", name="docadmin_back")
     */
    public function index(): Response
    {


        return $this->render('back/index.html.twig', [
            'controller_name' => 'HomeBackController',
        ]);
    }
    /**
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|Response
     * @Route ("docadmin/ajouteraliment",name="docadmin_ajouterAliment")
     */
    public function ajoute(Request $request)
    {
        $aliment =new Aliment();
        $aliment->setPoid(100);
        $form =$this->createForm(AlimentType::class,$aliment);
        //  $form->add("Ajouter",SubmitType::class);
        $form->handleRequest($request);//gere requette envoyer par l'utlisateur
        if($form->isSubmitted())

        if($form->isSubmitted() && $form->isValid()){
            $aliment->setNutritionniste($this->getUser());
            $em=$this->getDoctrine()->getManager();

            $em->persist($aliment);
            $em->flush();
            return $this->redirectToRoute('docadmin_afficherAliment');
        }
        return $this->render("back/aliment/ajouterAliment.html.twig",
            [  'form'=> $form->createView(), ]);
    }


    /**
     * @param Request $request
     * @param $id
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|Response
     * @Route ("docadmin/modifieraliment/{id}",name="docadmin_modifierAliment")
     */
    public function modifier(Request $request,  $id,AlimentRepository $repoAliment)
    {

        $aliment =$repoAliment->find($id);
        //    $s =new String_() ;+


        $form =$this->createForm(AlimentType::class,$aliment);
        $form->add("modifier",SubmitType::class);
        $form->handleRequest($request);//gere requette envoyer par l'utlisateur

        if($form->isSubmitted() && $form->isValid()){

            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('docadmin_afficherAliment');
        }
        return $this->render("back/aliment/ajouterAliment.html.twig",
            [  'form'=> $form->createView(), ]);


    }

    /**
     * @param AlimentRepository $repo
     * @Route ("docadmin/afficherAliment",name="docadmin_afficherAliment")
     */
    public function afficher(AlimentRepository $repo,PaginatorInterface $paginator,Request $request)
    {
          $donnees=$repo->findAll();

//        $donnees=null;
//         if (( (in_array("ROLE_ADMIN", $this->getUser()->getRoles()) )))
//        {
//            $donnees=$repo->findAll();
//        }
//         elseif ( (in_array("ROLE_DOCTOR", $this->getUser()->getRoles()) ))
//         {
//             $donnees = $repo->findalimentbyNutrioniste( $this->getUser());
//         }// remove comment to  only show aliment you addit as doctor

        $aliment=$paginator->paginate(
            $donnees,
            /* query NOT result */
            $request->query->getInt('page', 1), /*numero de page en cours 1 par défaut*/
            7 /*limit per page*/
        );
        return $this->render("back/aliment/afficherAliment.html.twig",
            ["aliment"=>$aliment]

        );
    }


    /**
     * @Route ("/afficherAlimentfront",name="afficherAlimentfront")
     */
    public function afficherfront(AlimentRepository $repo)
    {
        return $this->render("front/aliment/afficherAliment.html.twig"

        );
    }

    /**
     * @param AlimentRepository $repo
     * @param $id
     * @Route ("/docadmin/supprimerAliment/{id}",name="docadmin_supprimerAliment")
     */
    function delete(AlimentRepository $repo ,$id)
    {
        $em=$this->getDoctrine()->getManager()  ;
        $aliment=$repo->find($id);
        $em->remove($aliment);
        $em->flush();
        return $this->redirectToRoute('docadmin_afficherAliment');
    }
    /**
     * @param AlimentRepository $repository
     * @Route ("/docadmin/rechercheAliment",name="docadmin_rechercheAliment")
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