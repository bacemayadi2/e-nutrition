<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\FicheConsultationRepository;
use App\Entity\FicheConsultation;
class FicheConsultationController extends AbstractController
{
    /**
     * @Route("FicheConsultation", name="fiche_consultation")
     */
    public function index(): Response
    {
        return $this->render('Back/fiche_consultation/afficherFicheConsultation.html.twig', [
            'controller_name' => 'FicheConsultationController',
        ]);
    }

    /**
     * @param FicheConsultationRepository $repository
     * @return \Symfony\Component\HttpFoundation\Response
     * @Route("afficherFicheConsultation",name="ficheAffiche")
     */

    public function Affiche(FicheConsultationRepository $repository)
    {

        $ficheConsultation=$repository->findAll();
        return $this->render('Back/fiche_consultation/afficherFicheConsultation.html.twig',
        ['ficheConsultation'=>$ficheConsultation]);


    }


}
