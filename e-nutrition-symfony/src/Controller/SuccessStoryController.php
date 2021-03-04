<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
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
}
