<?php

namespace App\Controller;

use App\Entity\Medicament;
use App\Repository\FicheConsultationRepository;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\MedicamentRepository;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

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

    /**
     * @param MedicamentRepository $repository
     * @return \Symfony\Component\HttpFoundation\Response
     * @Route("afficherMedicament/{id}",name="afficherMedicament")
     */

    public function AfficheMedicament(Request $request,PaginatorInterface $paginator,$id,MedicamentRepository $repository,FicheConsultationRepository $repoFiche)
    {
        $donnee=$repository->findBy(
            ['fiche'=>$id]
        );
        $medicament=$paginator->paginate(
            $donnee,
            /* query NOT result */
            $request->query->getInt('page', 1), /*numero de page en cours 1 par défaut*/
            1 /*limit per page*/
        );
        $fiche=$repoFiche->find($id);

        return $this->render('Back/medicament/afficherMedicament.html.twig',
            ['medicament'=>$medicament,'fiche'=>$fiche]);
    }



    /**
     * @param MedicamentRepository $repository
     * @return \Symfony\Component\HttpFoundation\Response
     * @Route("listeMedicament/{id}",name="listeMedicament")
     */


    public function listeMedicament($id,MedicamentRepository $repository)
    {


        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        $medicament=$repository->findBy(
            ['fiche'=>$id]
        );

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('Back/medicament/listeMedicaments.html.twig',[
            'medicament'=>$medicament,
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("ordonance.pdf", [
            "Attachment" => true
        ]);
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
    /**
     * @param MedicamentRepository $repository
     *@return \Symfony\Component\HttpFoundation\Response
     * @Route ("afficherDetailFiche/{id}",name="afficherDetailFiche")
     */
    public function afficherDetailFiche(FicheConsultationRepository $repo,$id,Request $request,MedicamentRepository $repository,PaginatorInterface $paginator)
    {
        $donnee=$repository->findBy(
            ['fiche'=>$id]

        );
        $medicament=$paginator->paginate(
            $donnee,
            /* query NOT result */
            $request->query->getInt('page', 1), /*numero de page en cours 1 par défaut*/
            7 /*limit per page*/
        );
        $fiche=$repo->find($id);
        return $this->render("front/fiche_consultation/DetailFiche.html.twig",
            ['medicament'=>$medicament,'fiche'=>$fiche]

        );
    }

    /**
     * @param MedicamentRepository $repository
     * @param Request $request
     * @param PaginatorInterface $paginator
     * @param NormalizerInterface $normalizer
     * @return Response
     * @Route  ("Search_medicament/{id}",name="Search_medicament")
     */

    public function Recherche(FicheConsultationRepository $repo,MedicamentRepository $repository,Request $request,PaginatorInterface $paginator,NormalizerInterface $normalizer,$id)
    {


//        $aliment=$paginator->paginate(
//            $donnees,
//            /* query NOT result */
//            $request->query->getInt('page', 1), /*numero de page en cours 1 par défaut*/
//            7/*limit per page*/
//        );
//        return $this->render("back/aliment/afficherAliment.html.twig",
//            ["aliment"=>$aliment] );

        $repository = $this->getDoctrine()->getRepository(Medicament::class);
        $nom=$request->get('searchValue');

         $fiche=$repo->find($id);
        $medicament=$repository->findmedicamentbynom($nom,$fiche);
//        $aliment=$paginator->paginate(
//            $donnees,
//            /* query NOT result */
//            $request->query->getInt('page', 1), /*numero de page en cours 1 par défaut*/
//            7/*limit per page*/
//        );
        $jsonContent = $normalizer->normalize($medicament, 'json',['groups'=>'medicaments']);
        $retour =json_encode($jsonContent);
        return new Response($retour);
    }


}
