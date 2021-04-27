<?php

namespace App\Controller;

use App\Entity\ContenuMultimedia;
use App\Form\ContenuMultimediaType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ContenueMutlimediaController extends AbstractController
{
    /**
     * @Route ("affichervideo\{url}\{extension}",name="affichervideo")
     */
    public function afficherVideo($url,$extension)
    {
        return $this->render("contenue_mutlimedia/video.html.twig",
            ["url"=>$url,"extension"=>$extension]);

    }

    /**
     * @Route ("ajouterfichier",name="ajouterfichierr")
     */
    public function  ajouterFile(Request $request)
    {
        $contenuMultimedia = new ContenuMultimedia();
        $form = $this->createForm(ContenuMultimediaType::class, $contenuMultimedia);
        $form->add("Ajouter", SubmitType::class);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid())
        {
            $em=$this->getDoctrine()->getManager();
            $em->persist($contenuMultimedia);
            $em->flush();
            $response=  new Response($contenuMultimedia->getId());
            $response->headers->set('Content-Type', 'text/html');
            $response->setStatusCode($contenuMultimedia->getId());
            return $response;
          // $this->redirectToRoute(ajouterfichierr)
       //     return $this->redirectToRoute('Pub');
        }
        return $this->render('contenue_mutlimedia/index.html.twig ' ,['form'=>$form->createView()]);

    }// for java

}
