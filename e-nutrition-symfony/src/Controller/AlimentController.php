<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class AlimentController extends AbstractController
{
    /**
     * @Route("/ajouteraliment", name="aliment")
     */
    public function index(): Response
    {
        return $this->render('back/aliment/ajouterAliment.html.twig', [
            'controller_name' => 'AlimentController',
        ]);
    }
}
