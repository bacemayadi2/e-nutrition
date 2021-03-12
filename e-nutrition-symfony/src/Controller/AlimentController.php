<?php

namespace App\Controller;

use App\Entity\Aliment;
use App\Entity\CategorieAliment;
use App\Form\AlimentType;
use App\Repository\AlimentRepository;
use App\Repository\CategorieAlimentRepository;
use phpDocumentor\Reflection\Types\String_;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;


class AlimentController extends AbstractController
{
    /**
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|Response
     * @Route ("/ajouteraliment",name="ajouterAliment")
     */
    public function ajoute(Request $request)
    {
        $aliment =new Aliment();
        $form =$this->createForm(AlimentType::class,$aliment);
        $form->add("Ajouter",SubmitType::class);
        $form->handleRequest($request);//gere requette envoyer par l'utlisateur

        if($form->isSubmitted() && $form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $em->persist($aliment);
            $em->flush();
           return $this->redirectToRoute('afficherAliment');
        }
        return $this->render("back/aliment/ajouterAliment.html.twig",
            [  'form'=> $form->createView(), ]);
    }


    /**
     * @param Request $request
     * @param $id
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|Response
     * @Route ("modifieraliment/{id}",name="modifierAliment")
     */
    public function modifier(Request $request,  $id,AlimentRepository $repoAliment)
    {

        $aliment =$repoAliment->find($id);
        //    $s =new String_() ;+


        $form =$this->createForm(AlimentType::class,$aliment);
        $form->add("modifier",SubmitType::class);
        $form->handleRequest($request);//gere requette envoyé par l'utlisateur

        if($form->isSubmitted() && $form->isValid()){

            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('afficherAliment');
        }
        return $this->render("back/aliment/ajouterAliment.html.twig",
            [  'form'=> $form->createView(), ]);


    }

    /**
     * @param AlimentRepository $repo
     * @Route ("afficherAliment",name="afficherAliment")
     */
    public function afficher(AlimentRepository $repo)
    {
    $aliments=$repo->findAll();
    dump($aliments);
    return $this->render("back/aliment/afficherAliment.html.twig",
        ["aliments"=>$aliments]

    );
    }
    /**
     * @Route ("afficherAlimentfront",name="afficherAlimentfront")
     */
    public function afficherfront(AlimentRepository $repo)
    {
        return $this->render("front/aliment/afficherAliment.html.twig"

        );
    }

    /**
     * @param AlimentRepository $repo
     * @param $id
     * @Route ("/supprimerAliment/{id}",name="supprimerAliment")
     */
    function delete(AlimentRepository $repo ,$id)
    {
        $em=$this->getDoctrine()->getManager()  ;
        $aliment=$repo->find($id);
        dump($aliment);
        $em->remove($aliment);
        $em->flush();
        return $this->redirectToRoute('afficherAliment');
    }
}
