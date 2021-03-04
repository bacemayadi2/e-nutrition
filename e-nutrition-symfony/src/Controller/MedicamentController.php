<?php

namespace App\Controller;

use App\Repository\MedicamentRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class MedicamentController extends AbstractController
{
    /**
     * @Route("/medicament", name="medicament")
     */
    public function index(): Response
    {
        return $this->render('medicament/index.html.twig', [
            'controller_name' => 'MedicamentController',
        ]);
    }


    public function AfficheMedicament(MedicamentRepository $repository)
    {

        $medicament=$repository->findAll();
        return $this->render('Back/medicament/afficherMedicament.html.twig',
            ['medicament'=>$medicament]);
    }











}
