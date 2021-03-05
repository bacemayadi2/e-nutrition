<?php

namespace App\Controller;

use App\Entity\RendezVous;
use App\Entity\SuccessStory;
use App\Form\RendezVousType;
use App\Form\SuccessStoryType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class SuccessStoryController extends AbstractController
{
    /**
     * @Route("/success/story", name="success_story")
     */
    public function index(): Response
    {
        return $this->render('success_story/afficherMedicament.html.twig', [
            'controller_name' => 'SuccessStoryController',
        ]);
    }

    /**
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|Response
     * @Route ("CreateSuccess", name="Succ")
     */

    function CreateSuccess(Request $request)
    {
        $Success = new SuccessStory();
        $form = $this->createForm(SuccessStoryType::class, $Success);
        $form->add("Ajouter", SubmitType::class);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid())
        {
            $em = $this->getDoctrine()->getManager();
            $em->persist($Success);
            $em->flush();

            return $this->redirectToRoute('aff');
        }
        return $this->render('success_story/Success.html.twig ' ,['form'=>$form->createView()]);
    }
}
