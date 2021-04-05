<?php

namespace App\Controller;

use App\Entity\Challenge;
use App\Entity\Nutritionniste;
use App\Form\ChallengeType;
use App\Form\NutritionnisteType;
use App\Repository\ChallengeRepository;
use App\Repository\NutritionnisteRepository;
use App\Repository\PatientRepository;
use App\Repository\SecretaireRepository;
use App\Repository\UserRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
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

    /**
     * @Route("/DisplayChallenges", name="DisplayChallenges")
     */
    public function DisplayChallenges(ChallengeRepository $repo)
    {
        $this->denyAccessUnlessGranted('ROLE_ADMIN', null, "You must be ADMIN to access to this page");
        $challenges = $repo->findAll();
        return $this->render("back/challenges/DisplayChallenges.html.twig",['challenges'=>$challenges]);
    }

    /**
     * @Route("/CreateChallenge", name="CreateChallenge")
     */
    public function CreateChallenge(Request $request, ChallengeRepository $repo)
    {
        $this->denyAccessUnlessGranted('ROLE_ADMIN', null, "You must be ADMIN to access to this page");

        $challenge = new Challenge();
        $form = $this->createForm(ChallengeType::class, $challenge);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid())
        {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($challenge);
            $entityManager->flush();
        }
        return $this->render('back/challenges/createChallenge.html.twig', ['form'=>$form->createView()] );
    }

    /**
     * @Route("/DeleteChallenge/{id}", name="DeleteChallenge")
     */
    function DeleteChallenge(ChallengeRepository  $repo, $id)
    {
        $challenge = $repo->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($challenge);
        $em->flush();
        return $this->redirectToRoute('admin_DeleteChallenge');
    }
}
