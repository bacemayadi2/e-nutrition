<?php

namespace App\Controller;

use App\Entity\Challenge;
use App\Entity\ChallengeTag;
use App\Entity\Nutritionniste;
use App\Entity\TagsChallenge;
use App\Form\ChallengeType;
use App\Form\NutritionnisteType;
use App\Form\UserType;
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
        $this->denyAccessUnlessGranted('ROLE_ADMIN' , null, "You don't have privilege to access to this page");

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
        $tagsChallenge=new TagsChallenge();
        return $this->render("back/challenges/displayChallenges.html.twig",['challenges'=>$challenges]);
    }

    /**
     * @Route("/CreateChallenge", name="CreateChallenge")
     */
    public function CreateChallenge(Request $request, ChallengeRepository $repo)
    {
        $this->denyAccessUnlessGranted('ROLE_ADMIN', null, "You must be ADMIN to access to this page");

        $challenge = new Challenge();
        $image=new ChallengeTag();
        $challenge->addTagChallenge($image);
        $form = $this->createForm(ChallengeType::class, $challenge);
        $form->add("Ajouter", SubmitType::class);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid())
        {
            $entityManager = $this->getDoctrine()->getManager();
            $challenge->getTagChallenge()[0]->setUser($this->getUser());
            $entityManager->persist($challenge);
            $entityManager->flush();
            return $this->redirectToRoute('admin_DisplayChallenges');
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
        return $this->redirectToRoute('admin_DisplayChallenges');
    }

    /**
     * @Route("UpdateChallenge/{id}", name="UpdateChallenge")
     */
    function UpdateChallenge(ChallengeRepository  $repo, Request $request, $id)
    {
        $challenge = $repo->find($id);

        $form = $this->createForm(ChallengeType::class, $challenge);
        $form->add("Modifier", SubmitType::class);


        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid())
        {
            $em = $this->getDoctrine()->getManager();

            $em->flush();
            return $this->redirectToRoute('admin_DisplayChallenges');
        }
        return $this->render('back/challenges/updateChallenge.html.twig', ['form'=>$form->createView()]);
    }
}
