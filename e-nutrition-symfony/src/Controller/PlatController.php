<?php

namespace App\Controller;

use App\Entity\Plat;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class PlatController extends AbstractController
{
    /**
     * @Route("/ajouterplat", name="plat")
     */
    public function ajouterPlat(Request $request): Response
    {
        $plat =new Plat();
        $form =$this->createForm(AlimentType::class,$aliment);
        $form->add("Ajouter",SubmitType::class);
        $form->handleRequest($request);//gere requette envoyer par l'utlisateur

        if($form->isSubmitted() && $form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $em->persist($aliment);
            $em->flush();
            return $this->redirectToRoute('afficherAliment');
        }
        $categorie=$repo->findAll();
        return $this->render("back/aliment/ajouterAliment.html.twig",
            [ 'categorie' => $categorie , 'form'=> $form->createView(), ]);
    }


}
