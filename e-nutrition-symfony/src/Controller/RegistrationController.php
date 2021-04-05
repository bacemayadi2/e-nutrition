<?php

namespace App\Controller;

use App\Entity\Nutritionniste;
use App\Entity\Patient;
use App\Entity\Secretaire;
use App\Entity\Utilisateur;
use App\Form\NutritionnisteType;
use App\Form\PatientType;
use App\Form\RegistrationFormType;
use App\Form\SecretaireType;
use App\Security\LoginFormAuthenticator;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Security\Guard\GuardAuthenticatorHandler;

class RegistrationController extends AbstractController
{


    /**
     * @Route("/registerDoctor", name="app_register_doctor")
     */
    public function registerDoctor(Request $request, UserPasswordEncoderInterface $passwordEncoder, GuardAuthenticatorHandler $guardHandler, LoginFormAuthenticator $authenticator): Response
    {
        $user = new Nutritionniste();
        $form = $this->createForm(NutritionnisteType::class, $user);
        $form->add("Ajouter", SubmitType::class);

        $form->handleRequest($request);

        $user->setRoles(["ROLE_DOCTOR", "ROLE_SECRETAIRE", "ROLE_PATIENT"]);
//        foreach ($user->getSecretaire() as $secretaire )
//        {
//            $secretaire->setNutritionniste($user);
//            $user->removeSecretaire($secretaire);
//
//        } for modifier

        if ($form->isSubmitted() && $form->isValid())
        {
            foreach ($user->getSecretaire() as $secretaire )
            {
                $secretaire->setNutritionniste($user);

            }            // encode the plain password
            $user->setPassword( $passwordEncoder->encodePassword( $user, $form->get('plainPassword')->getData()));

            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($user);
            $entityManager->flush();
            // do anything else you need here, like send an email

            return $guardHandler->authenticateUserAndHandleSuccess(
                $user,
                $request,
                $authenticator,
                'main' // firewall name in security.yaml
            );
        }

        return $this->render('registration/registerDoctor.html.twig', ['registrationForm'=>$form->createView()] );
    }

    /**
     * @Route("/registerPatient", name="app_register_patient")
     */
    public function registerPatient(Request $request, UserPasswordEncoderInterface $passwordEncoder, GuardAuthenticatorHandler $guardHandler, LoginFormAuthenticator $authenticator): Response
    {
        $user = new Patient();
        $form = $this->createForm(PatientType::class, $user);
        $form->add("Ajouter", SubmitType::class);

        $form->handleRequest($request);

        $user->setRoles(["ROLE_PATIENT"]);

        if ($form->isSubmitted() && $form->isValid()) {
            // encode the plain password
            $user->setPassword( $passwordEncoder->encodePassword( $user, $form->get('plainPassword')->getData()));

            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($user);
            $entityManager->flush();
            // do anything else you need here, like send an email

            return $guardHandler->authenticateUserAndHandleSuccess(
                $user,
                $request,
                $authenticator,
                'main' // firewall name in security.yaml
            );
        }
        return $this->render('registration/registerPatient.html.twig', ['registrationForm'=>$form->createView()] );
    }

    /**
     * @Route("/registerSecretaire", name="app_register_secretaire")
     */
    public function registerSecretaire(Request $request, UserPasswordEncoderInterface $passwordEncoder, GuardAuthenticatorHandler $guardHandler, LoginFormAuthenticator $authenticator): Response
    {
        $user = new Secretaire();
        $form = $this->createForm(SecretaireType::class, $user);
        $form->add("Ajouter", SubmitType::class);

        $form->handleRequest($request);

        $user->setRoles(["ROLE_SECRETAIRE", "ROLE_PATIENT"]);

        if ($form->isSubmitted() && $form->isValid()) {
            // encode the plain password

            $user->setPassword( $passwordEncoder->encodePassword( $user, $form->get('plainPassword')->getData()));

            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($user);
            $entityManager->flush();
            // do anything else you need here, like send an email

            return $guardHandler->authenticateUserAndHandleSuccess(
                $user,
                $request,
                $authenticator,
                'main' // firewall name in security.yaml
            );
        }
        return $this->render('registration/registerSecretaire.html.twig', ['registrationForm'=>$form->createView()] );
    }

    /**
     * @Route("/register", name="register")
     */
    public function Register(Request $request): Response
    {
        $form = $this->createFormBuilder()
            ->add('typeCompte', ChoiceType::class, ['choices' => ['Choisir votre type de compte'=> 0, 'Patient' => 'Patient', 'Nutritionniste' => 'Nutritionniste', 'Secrétaire' => 'Secrétaire']])
            ->add('Suivant', SubmitType::class)
            ->getForm();

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid())
        {
            // data is an array with "name", "email", and "message" keys
            $data = $form->getData();

            switch ($data['typeCompte'])
            {
                case "Nutritionniste": return $this->redirectToRoute('app_register_doctor');
                case "Patient": return $this->redirectToRoute('app_register_patient');
                case "Secrétaire": return $this->redirectToRoute('app_register_secretaire');
            }
        }
        return $this->render('front/users/register.html.twig',  ['form'=>$form->createView()]);
    }
}
