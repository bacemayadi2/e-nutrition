<?php

namespace App\Controller;

use App\Entity\Evaluation;
use App\Entity\Nutritionniste;
use App\Entity\Patient;
use App\Entity\Student;
use App\Entity\TagsChallenge;
use App\Entity\TagsUser;
use App\Entity\TagUtilisateur;
use App\Entity\Utilisateur;
use App\Form\EvaluationType;
use App\Form\NutritionnisteType;
use App\Form\PatientType;
use App\Form\SecretaireType;
use App\Form\StudentType;
use App\Form\TagsChallengeType;
use App\Form\TagsUserType;
use App\Form\UserType;
use App\Repository\ChallengeRepository;
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

/**
 * @Route("/user", name="user_")
 */
class UserController extends AbstractController
{
    /**
     * @Route("/profileUser", name="profileUser")
     */
    public function profileUser(): Response
    {
        return $this->render('front/users/profileUser.html.twig', ['controller_name' => 'UserController',]);
    }

//__________________________________________Update users________________________________________________________________

    /**
     * @Route("/UpdateUser/{id}", name="UpdateUser")
     */
    function UpdateUser(PatientRepository $p, NutritionnisteRepository $d, SecretaireRepository $s, Request $request, $id)
    {
        $userp = $p->find($id);
        $userd = $d->find($id);
        $users = $s->find($id);

        if ($d instanceof Nutritionniste)
        {
            $form = $this->createForm(NutritionnisteType::class, $userd);
            $form->add("Modifier", SubmitType::class);

            $form->handleRequest($request);

            if ($form->isSubmitted() && $form->isValid())
            {
                $em = $this->getDoctrine()->getManager();

                $em->flush();
                return $this->redirectToRoute('user_profileUser');
            }
            return $this->render('front/users/updateDoctor.html.twig', ['form'=>$form->createView()]);
        }//----------------------------------------------------------------------------------------------------------------
        else if ($p instanceof Patient)
        {
            $form = $this->createForm(PatientType::class, $userp);
            $form->add("Modifier", SubmitType::class);

            $form->handleRequest($request);

            if ($form->isSubmitted() && $form->isValid())
            {
                $em = $this->getDoctrine()->getManager();

                $em->flush();
                return $this->redirectToRoute('user_profileUser');
            }
            return $this->render('front/users/updatePatient.html.twig', ['form'=>$form->createView()]);
        }

//----------------------------------------------------------------------------------------------------------------

        $form = $this->createForm(SecretaireType::class, $users);
        $form->add("Modifier", SubmitType::class);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid())
        {
            $em = $this->getDoctrine()->getManager();

            $em->flush();
            return $this->redirectToRoute('user_profileUser');
        }
        return $this->render('front/users/updateSecretaire.html.twig', ['form'=>$form->createView()]);


//----------------------------------------------------------------------------------------------------------------
    }


    /**
     * @Route("/UpdateDoctor/{id}", name="UpdateDoctor")
     */
    function UpdateDoctor(NutritionnisteRepository  $repo, Request $request, $id)
    {
        $user = $repo->find($id);

        $form = $this->createForm(NutritionnisteType::class, $user);
        $form->add("Modifier", SubmitType::class);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid())
        {
            $em = $this->getDoctrine()->getManager();

            $em->flush();
            return $this->redirectToRoute('user_profileUser');
        }
        return $this->render('front/users/updateDoctor.html.twig', ['form'=>$form->createView()]);
    }

    /**
     * @Route("/UpdatePatient/{id}", name="UpdatePatient")
     */
    function UpdatePatient(PatientRepository  $repo, Request $request, $id)
    {
        $user = $repo->find($id);

        $form = $this->createForm(PatientType::class, $user);
        $form->add("Modifier", SubmitType::class);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid())
        {
            $em = $this->getDoctrine()->getManager();

            $em->flush();
            return $this->redirectToRoute('user_profileUser');
        }
        return $this->render('front/users/updatePatient.html.twig', ['form'=>$form->createView()]);
    }

    /**
     * @Route("/UpdateSecretaire/{id}", name="UpdateSecretaire")
     */
    function UpdateSecretaire(SecretaireRepository  $repo, Request $request, $id)
    {
        $user = $repo->find($id);

        $form = $this->createForm(SecretaireType::class, $user);
        $form->add("Modifier", SubmitType::class);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid())
        {
            $em = $this->getDoctrine()->getManager();

            $em->flush();
            return $this->redirectToRoute('user_profileUser');
        }
        return $this->render('front/users/updateSecretaire.html.twig', ['form'=>$form->createView()]);
    }
 //______________________________________________________________________________________________________________________



    /**
    * @Route("/searchAjax", name="searchAjax")
    */
    public function searchAjax(NutritionnisteRepository $repo, Request $request, NormalizerInterface $Normalizer)
     {
         $repo = $this -> getDoctrine() -> getRepository(Nutritionniste::class);

         $requestString = $request -> get('search');

         $doctors = $repo -> findByName($requestString);

         $jsonContent = $Normalizer->normalize($doctors, 'json',['groups'=>'doctors']);

         $retour = json_encode($jsonContent);

         return new Response($retour);
     }

    /**
     * @Route("/affdoctor/{id}", name="affdoctor")
     */
    function affdoctor(NutritionnisteRepository $repo, $id)
    {
        $user = $repo->find($id);

        return $this->render('front/users/profileUser.html.twig', ['medecin'=>$user]);
    }

    /**
     * @Route("/SearchDoctor", name="SearchDoctor")
     */
    function SearchDoctor(NutritionnisteRepository $repo, Request $request)
    {
        $user = $repo->findAll();

        return $this->render('front/users/SearchDoctor.html.twig', ['doctors'=>$user]);
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

            return $this->redirectToRoute('user_profileMedecin', array('id' => $id));
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
        $tagsuser = new TagsUser();
        $form = $this->createForm(TagsUserType::class, $tagsuser);
        $form->add("Ajouter", SubmitType::class);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid())
        {
            foreach ($tagsuser->getTags() as $tag)
            {
                $tag->setIsPhotoDeProfile(false);
                $users->addTagUtilisateur($tag);
            }

            $em = $this->getDoctrine()->getManager();
            $em->flush();
            //$em->remove($tagsuser);
            $em->flush();
        }
        return $this->render("front/users/gallery.html.twig", ['users'=>$users, 'form'=>$form->createView()]);
    }

    /**
     * @Route("/DisplayChallenges", name="DisplayChallenges")
     */
    public function DisplayChallenges(ChallengeRepository $repo)
    {
        $challenges = $repo->findAll();
        return $this->render("front/challenges/displayChallenges.html.twig",['challenges'=>$challenges]);
    }

    /**
     * @Route("/JoinChallenge/{idc}/{idp}", name="JoinChallenge")
     */
    public function JoinChallenge($idc, $idp, ChallengeRepository $repo, PatientRepository $repoPatient)
    {
        $this->denyAccessUnlessGranted('ROLE_PATIENT', null, "You must be authenticated to join this event!!!");

        $challenge = $repo->find($idc);

        $user = $repoPatient->find($idp);

        $challenge->addParticipant($user);

        return $this->redirectToRoute('user_DisplayChallenges');
    }
    /**
     * @Route("/setphotodeprofile/{id}/{idtag}", name="setprofile")
     */
    public function setphotoprofile(Request $request, UserRepository $repo, $id,$idtag)
    {
        $users = $repo->find($id);



            foreach ($users->getTagUtilisateur() as $tag)
            {
                if ($tag->getId()!=$idtag) {
                    $tag->setIsPhotoDeProfile(false);
                }
                else
                    $tag->setIsPhotoDeProfile(true);

            }

            $em = $this->getDoctrine()->getManager();
            $em->flush();

        return $this->redirectToRoute('user_profileUser');
    }

    /**
     * @Route("/ChallengeDetails/{id}", name="ChallengeDetails")
     */
    public function ChallengeDetails($id, ChallengeRepository $repo, PatientRepository $repoPatient,Request $request)
    {
        $this->denyAccessUnlessGranted('ROLE_PATIENT', null, "You must be authenticated to join this event!!!");
        $tagsChallenge=new TagsChallenge();
        $form = $this->createForm(TagsChallengeType::class, $tagsChallenge);

        $form->add("Ajouter", SubmitType::class);

        $form->handleRequest($request);
        $challenge = $repo->find($id);


        if ($form->isSubmitted() && $form->isValid())
        {
            foreach ($tagsChallenge->getTags() as $tag)
            {
                $tag->setUser($this->getUser());
                $challenge->addTagChallenge($tag);
                //set tag for user also
                $taguser = new TagUtilisateur();
                $taguser->setContenuMultimedia($tag->getContenuMultimedia());
                $taguser->setIsPhotoDeProfile(false);
                $taguser->setUtilisateur($this->getUser());
                $this->getUser()->addTagUtilisateur($taguser);


            }

            $em = $this->getDoctrine()->getManager();
            $em->flush();
            //$em->remove($tagsuser);
            $em->flush();
            return $this->redirectToRoute('user_ChallengeDetails' ,['id'=> $id] );

        }

        return $this->render("front/challenges/challengeDetails.html.twig",['challenge'=>$challenge,'form'=>$form->createView()]);
    }
}
