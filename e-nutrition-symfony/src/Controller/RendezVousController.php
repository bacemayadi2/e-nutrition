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
    public function index(RendezVousRepository $rep)
    {
        $rendezs= $rep->findAll();
        $rdv = [];
        foreach($rendezs as $rendez){
            $rdv[]=[
                'id'=> $rendez->getId(),
                'date'=> $rendez->getDate()->format('Y-m-d H:i:s'),
                'description'=> $rendez->getDescription()
            ];


    }
        $data = json_encode($rdv);
        return $this->render('rendez_vous/calendar.html.twig', compact('data'));



    }

    /**
     * @param RendezVousRepository $repository
     * @return Response
     * @Route("AfficheR", name="aff")

     */
    public function AfficherRendezVous(RendezVousRepository $repository ){

        //$repo= $this->getDoctrine()->getRepository(RendezVous::class   );
        $rendezVous=$repository->findAll();

        return $this->render('rendez_vous/listeRDV.html.twig', [
            'controller_name' => 'RendezVousController',
            'rendezVous' => $rendezVous,
        ]);
    }

    /**
     * @param $id
     * @param RendezVousRepository $repository
     * @return \Symfony\Component\HttpFoundation\RedirectResponse
     * @Route("/rendez_vous/del/{id}", name="delete")
     */
    public function Delete($id , RendezVousRepository $repository){
        $rendezVous=$repository->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($rendezVous);
        $em->flush();
        return $this->redirectToRoute('aff');
    }
    /**
     * @Route("CreateRendezvous", name="rdv")
     */
    function CreateRendezVous(Request $request)
    {
        $RendezVous = new RendezVous();
        $form = $this->createForm(RendezVousType::class, $RendezVous);
        $form->add("Ajouter", SubmitType::class);


        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid())
        {

            $em = $this->getDoctrine()->getManager();
            $em->persist($RendezVous);
            $em->flush();

            return $this->redirectToRoute('aff');
        }
        return $this->render('rendez_vous/rendezVous.html.twig ' ,['form'=>$form->createView()]);
    }
}
