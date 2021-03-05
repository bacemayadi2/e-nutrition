<?php

namespace App\Controller;

use App\Entity\Plat;
use App\Form\PlatType;
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
        $form =$this->createForm(PlatType::class,$plat);
        $form->add("Ajouter",SubmitType::class);
        $form->handleRequest($request);//gere requette envoyer par l'utlisateur

        if($form->isSubmitted() && $form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $em->persist($plat);
            $em->flush();
            return $this->redirectToRoute('afficherAliment');
        }
        return $this->render("back/aliment/ajouterAliment.html.twig",
            [  'form'=> $form->createView(), ]);
    }


}
