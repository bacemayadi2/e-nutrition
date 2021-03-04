<?php

namespace App\Controller;

use App\Entity\RendezVous;
use App\Form\RendezVousType;
use App\Repository\RendezVousRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
class RendezVousController extends AbstractController
{
    /**
     * @Route("/rendez_vous", name="rendez_vous")
     */
    public function index(): Response
    {
        return $this->render('rendez_vous/afficherMedicament.html.twig', [
            'controller_name' => 'RendezVousController',
        ]);

    }
    /**
     * @Route("/rendez_vous/{id}", name="agenda")
     */
    public function getRendezVous($id , RendezVousRepository $repository ){

        //$repo= $this->getDoctrine()->getRepository(RendezVous::class   );
        $rendezVous=$repository->find($id);

        return $this->render('rendez_vous/rendezVous.html.twig', [
            'controller_name' => 'RendezVousController',
            'rendezVous' => $rendezVous,
        ]);
    }
    /**
     * @Route("/rendez_vous/del/{id}", name="delete")
     */
    public function Delete($id , RendezVousRepository $repository){
        $rendezVous=$repository->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($rendezVous);
        $em->flush();
        return $this->redirectToRoute('rendez_vous');
    }
    /**
     * @Route("CreateRendezvous", name="rdv")
     */
    function CreateRendezVous(Request $request)
    {
        $user = new RendezVous();
        $form = $this->createForm(RendezVousType::class, $user);
        $form->add("Ajouter", SubmitType::class);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid())
        {
            $em = $this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();
            dump($form);
            //return $this->redirectToRoute('CreateUser');
        }
        return $this->render('rendez_vous/rendezVous.html.twig ' ,['form'=>$form->createView()]);
    }
}
