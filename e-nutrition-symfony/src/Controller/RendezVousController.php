<?php

namespace App\Controller;

use App\Repository\RendezVousRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class RendezVousController extends AbstractController
{
    /**
     * @Route("/rendez_vous", name="rendez_vous")
     */
    public function index(): Response
    {
        return $this->render('rendez_vous/index.html.twig', [
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
}
