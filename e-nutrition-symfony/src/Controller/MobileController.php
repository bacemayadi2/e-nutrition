<?php

namespace App\Controller;

use App\Entity\Patient;
use App\Entity\Utilisateur;
use App\Repository\ChallengeRepository;
use Doctrine\ORM\EntityManager;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\SerializerInterface;

/**
 * @Route ("/api",name="api_")
 */
class MobileController extends AbstractController
{
    /**
     * @Route ("/displayCallenges",name="displayCallenges")
     */
    public function afficherfrontallapi(ChallengeRepository $repo,PaginatorInterface $paginator,Request $request, SerializerInterface $serializerInterface)
    {
        $challenges=$repo->findAll();

        $jsonContent = $serializerInterface->serialize($challenges, 'json', ['groups'=>'challenges:read']);
        //dump($jsonContent);
        // On instancie la réponse

        $response = new Response($jsonContent);

        // On ajoute l'entête HTTP
        $response->headers->set('Content-Type', 'application/json');

        // On envoie la réponse
        return $response;
    }

    /**
     * @Route ("/RegisterPatient",name="RegisterPatient")
     */
    public function RegisterPatient(Request $request, SerializerInterface $serializer, EntityManager $em)
    {
        $content = $request->getContent();
        $data = $serializer->deserialize($content, Patient::class, 'json');
        $em->persist($data);
        $em->flush();
        return new Response('Votre compte a été créer');
    }
}
