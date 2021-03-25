<?php

namespace App\Controller;

use App\Entity\Nutritionniste;
use App\Entity\Patient;
use App\Entity\Student;
use App\Form\NutritionnisteType;
use App\Form\PatientType;
use App\Form\StudentType;
use App\Form\UserType;
use App\Repository\StudentRepository;
use App\Repository\UserRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
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
     * @Route("/profileUser", name="profileUser")
     */
    public function profileUser(): Response
    {
        return $this->render('front/users/profileUser.html.twig', ['controller_name' => 'UserController',]);
    }

    /**
     * @Route("/admin/DisplayUsers", name="DisplayUsers")
     */
    public function DisplayUsers(UserRepository $repo)
    {
        $users = $repo->findAll();
        return $this->render("back/users/DisplayUsers.html.twig",['users'=>$users]);
    }

    /**
     * @Route("CreateUser", name="CreateUser")
     */
    function CreateUser(Request $request)
    {
        $choice = $request->get('typeCompte');
        $user = new Nutritionniste();

        $form = $this->createForm(NutritionnisteType::class, $user);
//        $form = $this->createForm(UserType::class, $user);
        $form->add("Ajouter", SubmitType::class);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid())
        {
            $em = $this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();
            return $this->redirectToRoute('CreateUser');
        }
        return $this->render('front/users/createUser.html.twig', ['form'=>$form->createView()]);
    }

    /**
     * @Route("UpdateUser/{id}", name="UpdateUser")
     */
    function UpdateUser(UserRepository  $repo, Request $request, $id)
    {
        $user = $repo->find($id);

        $form = $this->createForm(UserType::class, $user);
        $form->add("Valider", SubmitType::class);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid())
        {
            $em = $this->getDoctrine()->getManager();

            $em->flush();
            return $this->redirectToRoute('DisplayUsers');
        }
        return $this->render('front/users/updateUser.html.twig', ['form'=>$form->createView()]);
    }

    /**
     * @Route("/admin/DeleteUser/{id}", name="DeleteUser")
     */
    function DeleteUser(UserRepository  $repo, $id)
    {
        $user = $repo->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($user);
        $em->flush();
        return $this->redirectToRoute('DisplayUsers');
    }

    /**
     * @Route("/SearchDoctor", name="SearchDoctor")
     */
    function SearchDoctor(Request $request, UserRepository $repo)
    {
        $searchBy = $request->get('searchChoice');
        $input = $request->get('search'); // $input = $_POST['search']
        $users = 0;
        switch ($searchBy)
        {
            case "nom": $users = $repo->findBy(['typeCompte' => $input]); break;
            case "prenom": $users = $repo->findBy(['prenom' => $input]); break;
            case "nom et prenom":  $users = $repo->findBy(['nom' => $input]); break;
            case "ville": $users = $repo->findBy(['ville' => $input]); break;
            default: echo "error !!!";
        }
        return $this->render('front/users/SearchDoctor.html.twig', ['users'=>$users]);
    }


}
