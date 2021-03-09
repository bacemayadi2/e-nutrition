<?php

namespace App\Controller;

use App\Repository\EtapeDePreparationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class EtapeDePreparationController extends AbstractController
{
    /**
     * @Route("/etape/de/preparation", name="etape_de_preparation")
     */
    public function index(): Response
    {
        return $this->render('etape_de_preparation/index.html.twig', [
            'controller_name' => 'EtapeDePreparationController',
        ]);
    }
    /**
     * @param EtapeDePreparationRepository $repo
     * @param $id
     * @param  $idp
     * @Route ("/supprimerEtapeDePreparationRepositoryplat/{id}/{idp}",name="supprimerEtapeDePreparationRepositoryplat")
     */
    function delete(EtapeDePreparationRepository $repo ,$id,$idp)
    {
        $em=$this->getDoctrine()->getManager()  ;
        $etape=$repo->find($id);
        $em->remove($etape);
        $em->flush();
        return $this->redirectToRoute('afficherEtapeAliment' ,['id'=> $idp]);
    }

}
