<?php

namespace App\Controller;

use App\Repository\CompositionRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class CompositionController extends AbstractController
{
    /**
     * @Route("/composition", name="composition")
     */
    public function index(): Response
    {
        return $this->render('composition/index.html.twig', [
            'controller_name' => 'CompositionController',
        ]);
    }
    /**
     * @param CompositionRepository $repo
     * @param $id
     * @param  $idp
     * @Route ("docadmin/supprimerAlimentplat/{id}/{idp}",name="docadmin_supprimerAlimentplat")
     */
    function delete(CompositionRepository $repo , $id, $idp)
    {
        $em=$this->getDoctrine()->getManager()  ;
        $compostion=$repo->find($id);
        $em->remove($compostion);
        $em->flush();
        return $this->redirectToRoute('afficherEtapeAliment' ,['id'=> $idp] );
    }
}
