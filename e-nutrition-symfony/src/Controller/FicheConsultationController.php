<?php

namespace App\Controller;

use App\Entity\TagFicheConsultation;

use App\Repository\MedicamentRepository;
use Knp\Component\Pager\PaginatorInterface;
use phpDocumentor\Reflection\Types\This;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\FicheConsultationRepository;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use App\Form\FicheConsultationType;
use App\Entity\FicheConsultation;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class FicheConsultationController extends AbstractController
{
    /**
     * @param FicheConsultationRepository $repository
     * @return \Symfony\Component\HttpFoundation\Response
     * @Route("afficherFicheFront",name="afficherFicheFront")
     */


    public function AfficheFicheFront(FicheConsultationRepository $repository)
    {
        $ficheConsultation=$repository->findAll();
        return $this->render('front/fiche_consultation/ficheAffiche.html.twig', [
            'ficheConsultation' => $ficheConsultation]);

    }



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
     * @Route("afficherFicheConsultation",name="afficherFicheConsultation")
     */

    public function AfficheFiche(Request $request,PaginatorInterface $paginator,FicheConsultationRepository $repository)
    {

        $donnees=$repository->findAll();
        $ficheConsultation=$paginator->paginate(
            $donnees,
            /* query NOT result */
            $request->query->getInt('page', 1), /*numero de page en cours 1 par défaut*/
            7 /*limit per page*/
        );
        return $this->render('Back/fiche_consultation/afficherFicheConsultation.html.twig',
        ['ficheConsultation'=>$ficheConsultation]);
    }

    /**
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\Response
     * @route("ajouterFicheConsultation",name="ficheAjout")
     */

   public function AjouteFiche(Request $request,MedicamentRepository $repo)
   {
   $ficheConsultation=new FicheConsultation();
   $tagImage=new TagFicheConsultation();
   $ficheConsultation->addTagFicheConsultation($tagImage);
   $form=$this->createForm(FicheConsultationType::class,$ficheConsultation);
  // $form->add('Ajouter',SubmitType::class);
   $form->handleRequest($request);
       if($form->isSubmitted()) {
           dump($form);
           $ficheConsultation->getPatient();
      }
   if($form->isSubmitted()&& $form->isValid() ){

       $em=$this->getDoctrine()->getManager();
       $em->persist($ficheConsultation);
       $em->flush();
       return $this->redirectToRoute('afficherFicheConsultation');
   }

return $this->render('Back/fiche_consultation/ajouterFicheConsultation.html.twig',
    ['form'=>$form->createView()]);
   }


    /**
     * @route("supprimerFicheConsultation/{id}", name="deleteF")
     */

   public function Delete($id,FicheConsultationRepository $repository){
       $ficheConsultation=$repository->find($id);
       $em=$this->getDoctrine()->getManager();
       $em->remove($ficheConsultation);
       $em->flush();
       return $this->redirectToRoute('afficherFicheConsultation');
   }




    /**
     * @param Request $request
     * @param $id
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|Response
     * @route("updateFicheConsultation/{id}", name="updateF")
     */

 function Update(FicheConsultationRepository $repository,$id,Request $request){
     $ficheConsultation=$repository->find($id);
     $form=$this->createForm(FicheConsultationType::class,$ficheConsultation);
     $form->add('Update',SubmitType::class);
     $form->handleRequest($request);
     if($form->isSubmitted()&&$form->isValid()){

       $em=$this->getDoctrine()->getManager();
       $em->flush();
       return $this->redirectToRoute('afficherFicheConsultation');
     }

     return $this->render('Back/fiche_consultation/ajouterFicheConsultation.html.twig',
     ['form'=>$form->createView()]);

 }



}
