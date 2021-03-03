<?php

namespace App\Controller;

use App\Entity\Student;
use App\Entity\Utilisateur;
use App\Form\StudentType;
use App\Form\UserType;
use App\Repository\StudentRepository;
use App\Repository\UserRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class UserController extends AbstractController
{
    /**
     * @Route("/user", name="user")
     */
    public function index(): Response
    {
        return $this->render('Back/users/createUser.html.twig', ['controller_name' => 'UserController',]);
    }

    /**
     * @Route("/student/AfficheStudent", name="AfficheStudent")
     */
    public function AfficheStudent(UserRepository $repo)
    {
        $users = $repo->findAll();
        return $this->render("back/DisplayUsers.html.twig",['users'=>$users]);
    }

    /**
     * @Route("CreateUser", name="CreateUser")
     */
    function CreateUser(Request $request)
    {
        $user = new Utilisateur();
        $form = $this->createForm(UserType::class, $user);
        $form->add("Ajouter", SubmitType::class);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid())
        {
            $em = $this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();
            return $this->redirectToRoute('CreateUser');
        }
        return $this->render('back/users/createUser.html.twig', ['form'=>$form->createView()]);
    }

    /**
     * @Route("DeleteUser/{id}", name="DeleteUser")
     */
    function DeleteStudent(UserRepository  $repo, $id)
    {
        $user = $repo->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($user);
        $em->flush();
        return $this->redirectToRoute('DisplayUsers');
    }
}
