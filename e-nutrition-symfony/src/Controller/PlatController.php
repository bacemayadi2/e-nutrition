<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class PlatController extends AbstractController
{
    /**
     * @Route("/ajouterplat", name="plat")
     */
    public function ajouterPlat(): Response
    {
        return $this->render('back/plat/ajouterPlat.html.twig', [
            'controller_name' => 'PlatController',
        ]);
    }
}
