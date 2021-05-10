<?php

namespace App\Controller;

use App\Entity\Nutritionniste;
use App\Entity\TagFicheConsultation;

use App\Repository\MedicamentRepository;
use App\Repository\PatientRepository;
use App\Repository\UserRepository;
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
use Symfony\Component\Serializer\SerializerInterface;

class FicheConsultationController extends AbstractController
{
    /**
     * @param FicheConsultationRepository $repository
     * @return \Symfony\Component\HttpFoundation\Response
     * @Route("afficherFicheFront",name="afficherFicheFront")
     */


    public function AfficheFicheFront(Request $request,PaginatorInterface $paginator,FicheConsultationRepository $repository)
    {
        $donnees=$repository->findBy(['patient' => $this->getUser()->getId()]);
        $ficheConsultation=$paginator->paginate(
            $donnees,
            /* query NOT result */
            $request->query->getInt('page', 1), /*numero de page en cours 1 par défaut*/
            3 /*limit per page*/
        );
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

        $donnees=$repository->findBy(['nutritionniste' => $this->getUser()->getId()]);
        $ficheConsultation=$paginator->paginate(
            $donnees,
            /* query NOT result */
            $request->query->getInt('page', 1), /*numero de page en cours 1 par défaut*/
            3 /*limit per page*/
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

           $ficheConsultation->setNutritionniste($this->getUser());
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
     * @route("supprimerFicheConsultation/{id}", name="deleteFa")
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
     * @route("updateFicheConsultation/{id}", name="updateFa")
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

    /**
     * @Route("/stats",name="stats")
     */
    public function statistique(PatientRepository $repository,FicheConsultationRepository $repo){
//on va chercher le nombre des consultations par date
        $userid=$this->getUser()->getId();

        if (( (in_array("ROLE_ADMIN", $this->getUser()->getRoles()) ))) {

            $ficheConsultations=$repo->countByDate();
        }
        else {

            $ficheConsultations=$repo->countByDateAndNutritionniste($this->getUser());
        }
        $dates=[];
        $patients=$repository->find(18);

        $ficheConsultationCount=[];

//on démonte les données pour les séparer tel qu'attendu par CharJs

        foreach ($ficheConsultations as $ficheConsultation){

            $dates[]=$ficheConsultation['CreationDate'];
            $ficheConsultationCount[]=$ficheConsultation['count'];

        }


        //$ficheConsultationCount[]=count($patients->getFicheConsultations());
        return $this->render('Back/fiche_consultation/stats.html.twig',[

            'ficheConsultationCount'=>json_encode($ficheConsultationCount),
'dates'=>json_encode($dates),
        ]);
    }


    /**
     * @Route("/SearchFiche", name="SearchFiche")
     */
    public function search(Request $request,FicheConsultationRepository $repo)
    {
        $requestString = $request->get('q');
        $entities=null;
        if (( (in_array("ROLE_ADMIN", $this->getUser()->getRoles()) )))
        {
            $entities = $repo->findEntitiesByString($requestString);
        }
        else if ( (in_array("ROLE_DOCTOR", $this->getUser()->getRoles()) ))
        {
            $entities = $repo->findEntitiesByStringAndNutritionniste($requestString, $this->getUser());
        }

        if(!$entities) {
            $result['entities']['error'] = "Aucune fiche trouvée";
        } else {
            $result['entities'] = $this->getRealEntities($entities);
        }

        return new Response(json_encode($result));
    }

    public function getRealEntities($fiches)
    {

        foreach ($fiches as $f){
            $realEntities[$f->getId()] = [$f->getPatient()->getNom(),$f->getNutritionniste()->getNom(),$f->getCreationDate(),$f->getPoids(),$f->getTaille(),$f->getDescription(),$f->getSymptome(),$f->getApetit(),$f->calculerImc(),$f->getnbrOFTimeUsed()];
        }
        return $realEntities;
    }

    /**
     * @Route("/FicheListeMobile", name="FicheListeMobile")
     */
    public function FicheListe(FicheConsultationRepository $repo,SerializerInterface $serializerInterface)
    {
     $fiches=$repo->findAll();
     $json=$serializerInterface->serialize($fiches,'json',['groups'=>'fiche']);
    // dump($json);

        //$repo=$this->getDoctrine()->getRepository(FicheConsultation::class);
        //$FicheListe=$repo->findAll();

       // $jsonContent= $normalizer->normalize($FicheListe,'json');
       return new Response(json_encode($json));
    }




}
