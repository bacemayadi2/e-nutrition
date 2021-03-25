<?php

namespace App\Controller;

use App\Repository\UserRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/admin", name="admin_")
 */
class AdminController extends AbstractController
{
    /**
     * @Route("/admin", name="admin")
     */
    public function index(): Response
    {
        return $this->render('back/admin/index.html.twig', [ 'controller_name' => 'AdminController' ]);
    }

    /**
     * @Route("/DisplayUsers", name="DisplayUsers")
     */
    public function DisplayUsers(UserRepository $repo)
    {
        $users = $repo->findAll();
        return $this->render("back/admin/DisplayUsers.html.twig",['users'=>$users]);
    }
}
