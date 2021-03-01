<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class FicheConsultationController extends AbstractController
{
    /**
     * @Route("FicheConsultation", name="fiche_consultation")
     */
    public function index(): Response
    {
        return $this->render('Back/fiche_consultation/index.html.twig', [
            'controller_name' => 'FicheConsultationController',
        ]);
    }
}
