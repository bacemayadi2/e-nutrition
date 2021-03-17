<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class HomeBackController extends AbstractController
{
    /**
     * @Route("indexback", name="home_back")
     */
    public function index(): Response
    {
        return $this->render('back/home_back/index.html.twig', [
            'controller_name' => 'HomeBackController',
        ]);
    }
}
