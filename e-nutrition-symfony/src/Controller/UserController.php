<?php

namespace App\Controller;

use App\Entity\Evaluation;
use App\Entity\Nutritionniste;
use App\Entity\Patient;
use App\Entity\Student;
use App\Entity\Utilisateur;
use App\Form\EvaluationType;
use App\Form\NutritionnisteType;
use App\Form\PatientType;
use App\Form\StudentType;
use App\Form\UserType;
use App\Repository\EvaluationRepository;
use App\Repository\NutritionnisteRepository;
use App\Repository\PatientRepository;
use App\Repository\SecretaireRepository;
use App\Repository\StudentRepository;
use App\Repository\UserRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class UserController extends AbstractController
{
    /**
     * @Route("/profileUser", name="profileUser")
     */
    public function profileUser(): Response
    {
        return $this->render('front/users/profileUser.html.twig', ['controller_name' => 'UserController',]);
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
    * @Route("/searchAjax ", name="searchAjax")
    */
    public function searchAjax(Request $request, NormalizerInterface $Normalizer)
     {
         $repository = $this -> getDoctrine() -> getRepository(Nutritionniste::class);

         $requestString = $request -> get('search');

         $doctors = $repository -> findByName($requestString);

         $jsonContent = $Normalizer->normalize($doctors, 'json',['groups'=>'doctors']);

         $retour=json_encode($jsonContent);

         return new Response($retour);
     }

    /**
     * @Route("/SearchDoctor", name="SearchDoctor")
     */
    function SearchDoctor()
    {
        return $this->render('front/users/SearchDoctor.html.twig');
    }

    /**
     * @Route("/profileMedecin/{id}", name="profileMedecin")
     */
    function addEvaluation(Request $request, NutritionnisteRepository $repo,PatientRepository $patrepo,$id,EvaluationRepository $evRep):Response
    {
        $evaluation=new Evaluation();

        $form = $this->createForm(EvaluationType::class, $evaluation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid())
        {
            $evaluation->setCreatedAt(new \DateTime('now'));
            $patient=$patrepo->find($this->getUser());
            $evaluation->setPatient($patient);

            $nutri=$repo->find($id);
            $evaluation->setNutritionniste($nutri);

            $em = $this->getDoctrine()->getManager();
            $em->persist($evaluation);
            $em->flush();

            return $this->redirectToRoute('profileMedecin', array('id' => $id));
        }
        $medecin=$repo->findBy(['id' => $id]);
        $avg=$evRep->Average($medecin);
        return $this->render('front/users/profilMedecin.html.twig', ['medecin' => $medecin,'form'=>$form->createView(),'avg'=>$avg]);
    }

    /**
     * @Route("/gallery/{id}", name="gallery")
     */
    public function Gallery(Request $request, UserRepository $repo, $id)
    {
        $users = $repo->find($id);

        $form = $this->createForm(UserType::class, $users);
        $form->add("Ajouter", SubmitType::class);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid())
        {
            $em = $this->getDoctrine()->getManager();
            $em->flush();
        }
        return $this->render("front/users/gallery.html.twig", ['users'=>$users, 'form'=>$form->createView()]);
    }
}
