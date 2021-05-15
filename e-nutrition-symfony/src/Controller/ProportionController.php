<?php

namespace App\Controller;

use App\Entity\Aliment;
use App\Entity\Proportion;
use App\Entity\Utilisateur;
use App\Form\PlatType;
use App\Form\ProportionType;
use App\Repository\AlimentRepository;
use App\Repository\PatientRepository;
use App\Repository\PlatRepository;
use App\Repository\ProportionRepository;
use App\Repository\UserRepository;
use Knp\Component\Pager\PaginatorInterface;
use phpDocumentor\Reflection\Types\Collection;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\SerializerInterface;

class ProportionController extends AbstractController
{


    /**
     * @param ProportionRepository $repo
     * @param $id
     * @Route ("/supprimerproportions/{id}",name="supprimerproportionss")
     */
    function deleteproportions(ProportionRepository  $repop, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $proportion=$repop->find($id);
        $em->remove($proportion);
        $em->flush();
        return $this->redirectToRoute('Proportions');
    }
    /**
     * @param Request $request
     * @param $id
     * @param AlimentRepository $repo
     * @return \Symfony\Component\HttpFoundation\RedirectResponse
     * @Route ("/ajouterproportion/{id}/{quantite}",name="ajouterproportion")
     */
    public function ajoute(Request $request,$id,$quantite,AlimentRepository $repo)
    {
        $aliment=$repo->find($id);
        $proportion =new Proportion();
        $proportion->setPoid($quantite);
        $proportion->setPatient($this->getUser());
        $proportion->setAliment($aliment);
        $proportion->setDate(new \DateTime());
        $proportion->calculatenutritionvalue();
        $em=$this->getDoctrine()->getManager();
        $em->persist($proportion);
        $em->flush();
        return $this->redirectToRoute('Proportions');

    }

    /**
     * @param Request $request
     * @param $id
     * @param AlimentRepository $repo
     * @return \Symfony\Component\HttpFoundation\RedirectResponse
     * @Route ("/ajouterplatproportion/{id}/{quantite}",name="ajouterplatproportion")
     */
    public function ajouteplat(Request $request,$id,$quantite,PlatRepository $repo)
    {
        $plat=$repo->find($id);
        foreach ($plat->getCompostions() as $compo )
        {
            $proportion =new Proportion();
            $proportion->setPoid(($compo->getPoid()/$plat->getNbrportion())*$quantite);
            $proportion->setPatient($this->getUser());
            $proportion->setAliment($compo->getAliment());
            $proportion->setDate(new \DateTime());
            $proportion->calculatenutritionvalue();
            $em=$this->getDoctrine()->getManager();
            $em->persist($proportion);

        }
        $em->flush();
        return $this->redirectToRoute('Proportions');

    }
    /**
     * @Route("/proportion", name="proportion")
     */
    public function index(): Response
    {
        return $this->render('front/proportions/foodIntake.html.twig', [
            'controller_name' => 'ProportionController',
        ]);
    }

    /**
     * @param AlimentRepository $repo
     * @param PaginatorInterface $paginator
     * @param Request $request
     * @return Response
     * @Route ("Proportions",name="Proportions")

     */
    public function afficheraliment(AlimentRepository $repo,PaginatorInterface $paginator,Request $request,ProportionRepository  $repoP)
    {
        $donnees=$repo->findAll();
        $endDate = (new \DateTime());
        $startDate = (clone $endDate)->modify('- 1 day ');
        $oldproportions=$repoP->findbetwen2date($startDate,$endDate,$this->getUser());

        $proportion[]=null;


        $aliment=$paginator->paginate(
            $donnees,
            /* query NOT result */
            $request->query->getInt('page', 1), /*numero de page en cours 1 par défaut*/
            7 /*limit per page*/
        );
        $forms[]=null;
        $i=0;
//        foreach ($aliment as $a )
//        {
//            $proportion[$i]=new Proportion();
//            $proportion[$i]->setPoid($a->getPoid());
//            $forms[$i] =$this->createForm(ProportionType::class, $proportion[$i]);

//            $forms[$i] = $this->get('form.factory')->createNamed(
//                'form'.$i, // unique form name
//                ProportionType::class,
//                $proportion[$i]
//            );
//            $forms[$i]->handleRequest($request);


//            if ($forms[$i]->isSubmitted() && $forms[$i]->isValid()) {
//                // do what you want with $forms[$article->getId()]->getData()
//                // ...
//            }

//            dump($i);
//            $i=$i+1;
//        }
//        $f =array(
//            'for' => array_map(function ($form) {
//                return $form->createView();
//            }, $forms), );
        $totalCalories=0;
        $totalLipides=0;
        $totalProteins=0;
        $totalGlucides=0;
        foreach ($oldproportions as $p)
        {
            $p->calculatenutritionvalue();
            $totalCalories+=$p->getCalorie();
            $totalLipides+=$p->getLipides();
            $totalProteins+=$p->getProteines();
            $totalGlucides+=$p->getGlucides();
        }


        return $this->render("front/proportions/foodIntake.html.twig",
            ["aliment"=>$aliment,"proportions"=>$oldproportions,"tc"=>$totalCalories,"tl"=>$totalLipides,"tp"=>$totalProteins,"tg"=>$totalGlucides]

        );
    }


    /**
     * @Route ("rechercheAliment",name="rechercheAliment")
     */
    public function Recherche(AlimentRepository $repository,Request $request,PaginatorInterface $paginator,NormalizerInterface $normalizer)
    {


//        $aliment=$paginator->paginate(
//            $donnees,
//            /* query NOT result */
//            $request->query->getInt('page', 1), /*numero de page en cours 1 par défaut*/
//            7/*limit per page*/
//        );
//        return $this->render("back/aliment/afficherAliment.html.twig",
//            ["aliment"=>$aliment] );

        $repository = $this->getDoctrine()->getRepository(Aliment::class);
        $name=$request->get('searchValue');
        $donnees=$repository->findalimentbyname($name);

        $aliment=$repository->findalimentbyname($name);
//        $aliment=$paginator->paginate(
//            $donnees,
//            /* query NOT result */
//            $request->query->getInt('page', 1), /*numero de page en cours 1 par défaut*/
//            7/*limit per page*/
//        );
        $jsonContent = $normalizer->normalize($aliment, 'json',['groups'=>'aliments']);
        $retour =json_encode($jsonContent);
        return new Response($retour);
    }


    /**
     * @Route ("statintake/{choix}",name="statintake")
     */
    public function statistique(ProportionRepository $repo,Request $request,$choix){
        if  ($choix ==1)

            {
                $endDate = (new \DateTime());
                $startDate = (clone $endDate)->modify('- 1 day ');
                $proportion=$repo->countByDateAndpatientjour($this->getUser(),$startDate,$endDate);
                $form = $this->createFormBuilder()
                    ->add('kcal', ChoiceType::class, ['choices' => [ 'jour' => 'jour', 'mois' => 'mois', 'annees' => 'annees']])
                    ->add('valider', SubmitType::class)
                    ->getForm();

            }
        if ($choix ==2)
            {
                $endDate = (new \DateTime());
                $startDate = (clone $endDate)->modify('- 1 month ');
                $proportion=$repo->countByDateAndpatientmois($this->getUser(),$startDate,$endDate);
                $form = $this->createFormBuilder()
                    ->add('kcal', ChoiceType::class, ['choices' => [ 'mois' => 'mois', 'jour' => 'jour', 'annees' => 'annees']])
                    ->add('valider', SubmitType::class)
                    ->getForm();

            }
        if ($choix ==3)
            {
                $endDate = (new \DateTime());
                $startDate = (clone $endDate)->modify('- 1 year ');
                $proportion=$repo->countByDateAndpatientannee($this->getUser(),$startDate,$endDate);
                $form = $this->createFormBuilder()
                    ->add('kcal', ChoiceType::class, ['choices' => [ 'annees' => 'annees', 'jour' => 'jour', 'mois' => 'mois']])
                    ->add('valider', SubmitType::class)
                    ->getForm();

            }

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid())
        {
            // data is an array with "name", "email", and "message" keys
            $data = $form->getData();

            switch ($data['kcal'])
            {
                case "jour":
                    {
                        return $this->redirect($this->generateUrl('statintake', array(
                            'choix' => 1
                        )));
                       // return $this->redirectToRoute('statintake');

                    }
                case "mois":
                    {
                        return $this->redirect($this->generateUrl('statintake', array(
                            'choix' => 2
                        )));
                        //return $this->redirectToRoute('statintake');

                    }
                case "annees":
                {
                    return $this->redirect($this->generateUrl('statintake', array(
                        'choix' => 3
                    )));
                    //return $this->redirectToRoute('statintake');

                }
            }
        }
//on va chercher le nombre des consultations par date

        $dates=[];

        $proportionCount=[];
        $proportionCountcum=[];


//on démonte les données pour les séparer tel qu'attendu par CharJs
        foreach ($proportion as $p){

            $dates[]=$p['date'];
            $proportionCount[]=$p['count'];
            $lastindex=sizeof($proportionCountcum)-1;
            if ($lastindex!=-1)
            {
            $proportionCountcum[]=$p['count']+ $proportionCountcum[$lastindex];
            }
            else{
                $proportionCountcum[]=$p['count'];
            }


        }


        //$ficheConsultationCount[]=count($patients->getFicheConsultations());
        return $this->render('front/proportions/statintake.html.twig',[

            'proportionCount'=>json_encode($proportionCount),
            'proportionCountcum'=>json_encode($proportionCountcum),
            'dates'=>json_encode($dates),
            'form'=>$form->createView()
        ]);
    }

    /**
     * @param PlatRepository $repo
     * @Route ("api/getProportion/{iduser}",name="api_getProportion")
     */
    public function ProportionMobile(AlimentRepository $repo,PaginatorInterface $paginator,Request $request,ProportionRepository  $repoP, SerializerInterface $serializerInterface,$iduser,UserRepository $repouser)
    {
        //$donnees=$repo->findAll();
        $endDate = (new \DateTime());
        $startDate = (clone $endDate)->modify('- 1 day ');

        $oldproportions=$repoP->findbetwen2date($startDate,$endDate,$repouser->find($iduser));

        $proportion[]=null;


//        $aliment=$paginator->paginate(
//            $donnees,
//            /* query NOT result */
//            $request->query->getInt('page', 1), /*numero de page en cours 1 par défaut*/
//            7 /*limit per page*/
//        );

        $jsonContent = $serializerInterface->serialize($oldproportions, 'json', ['groups'=>'prportion:read']);
        //dump($jsonContent);
        // On instancie la réponse

        $response = new Response($jsonContent);

        // On ajoute l'entête HTTP
        $response->headers->set('Content-Type', 'application/json');

        // On envoie la réponse
        return $response;
    }
}


