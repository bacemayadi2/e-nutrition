<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class CategorieAlimentController extends AbstractController
{
    /**
     * @Route("/categorie/aliment", name="categorie_aliment")
     */
    public function index(): Response
    {
        return $this->render('categorie_aliment/index.html.twig', [
            'controller_name' => 'CategorieAlimentController',
        ]);
    }
}
