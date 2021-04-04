<?php

namespace App\Controller;

use App\Repository\NutritionnisteRepository;
use App\Repository\PatientRepository;
use App\Repository\SecretaireRepository;
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
     * @Route("/back", name="back")
     */
    public function index(): Response
    {
        $this->denyAccessUnlessGranted('ROLE_ADMIN', null, "You don't have privilege to access to this page");

        return $this->render('back/index.html.twig', [
            'controller_name' => 'HomeBackController',
        ]);
    }

    //--------------------------------   Display users   -----------------------------------------------------------------------------------------------

    /**
     * @Route("/DisplayUsers", name="DisplayUsers")
     */
    public function DisplayUsers(UserRepository $repo)
    {
        $this->denyAccessUnlessGranted('ROLE_ADMIN', null, "You must be ADMIN to access to this page");
        $users = $repo->findAll();

        echo  "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh";

        $tab = ['sami', 'feehri', 'salah'];

        echo "hhhhhhhhhhhh";

        foreach ($tab as $t)
        {
            echo " $t ";
        }

        echo " \n ";

        $ch = 'sami';

        foreach ($tab as $t)
        {
            unset($tab[$ch]);
        }

        echo "\n";

        foreach ($tab as $t)
        {
            echo " $t ";
        }

        return $this->render("back/users/DisplayUsers.html.twig",['users'=>$users]);
    }

    /**
     * @Route("/DisplayDoctors", name="DisplayDoctors")
     */
    public function DisplayDoctors(NutritionnisteRepository $repo)
    {
        $this->denyAccessUnlessGranted('ROLE_ADMIN', null, "You must be ADMIN to access to this page");
        $doctors = $repo->findAll();
        return $this->render("back/users/DisplayDoctors.html.twig",['doctors'=>$doctors]);
    }

    /**
     * @Route("/DisplayPatients", name="DisplayPatients")
     */
    public function DisplayPatients(PatientRepository $repo)
    {
        $this->denyAccessUnlessGranted('ROLE_ADMIN', null, "You must be ADMIN to access to this page");
        $users = $repo->findAll();
        return $this->render("back/users/DisplayPatients.html.twig",['users'=>$users]);
    }

    /**
     * @Route("/DisplaySecretaires", name="DisplaySecretaires")
     */
    public function DisplaySecretaires(SecretaireRepository $repo)
    {
        $this->denyAccessUnlessGranted('ROLE_ADMIN', null, "You must be ADMIN to access to this page");
        $users = $repo->findAll();
        return $this->render("back/users/DisplaySecretaires.html.twig",['users'=>$users]);
    }

    /**
     * @Route("/DeleteUser/{id}", name="DeleteUser")
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
     * @Route("/PromoteToAdmin/{id}", name="PromoteToAdmin")
     */
    public function PromoteToAdmin(UserRepository  $repo, $id)
    {
        $user = $repo->find($id);
        $user->setRoles(['ROLE_ADMIN']);
        return $this->redirectToRoute('admin_DisplayUsers');
    }

    /**
     * @Route("/RemoveRoleAdmin/{id}", name="RemoveRoleAdmin")
     */
    public function RemoveRoleAdmin(UserRepository  $repo, $id)
    {
        $user = $repo->find($id);

        $user->removeRoleAdmin();

        return $this->redirectToRoute('admin_DisplayUsers');
    }
}
