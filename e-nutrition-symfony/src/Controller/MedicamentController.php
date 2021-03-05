<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

use App\Repository\MedicamentRepository;

class MedicamentController extends AbstractController
{
    /**
     * @Route("medicament", name="medicament")
     */
    public function index(): Response
    {
        return $this->render('Back/medicament/afficherMedicament.html.twig', [
            'controller_name' => 'MedicamentController',
        ]);
    }
    /**
     * @param MedicamentRepository $repository
     * @return \Symfony\Component\HttpFoundation\Response
     * @Route("afficherMedicament",name="afficherMedicament")
     */


    public function AfficheMedicament(MedicamentRepository $repository)
    {

        $medicament=$repository->findAll();
        return $this->render('Back/medicament/afficherMedicament.html.twig',
            ['medicament'=>$medicament]);
    }


    /**
     * @route("supprimerMedicament/{id}", name="deleteM")
     */

    public function Delete($id,MedicamentRepository $repository){
        $medicament=$repository->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($medicament);
        $em->flush();
        return $this->redirectToRoute('afficherMedicament');
    }


}
