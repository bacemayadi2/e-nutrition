<?php

namespace App\Controller;

use App\Entity\Composition;
use App\Entity\Plat;
use App\Form\PlatType;
use App\Repository\AlimentRepository;
use App\Repository\CompositionRepository;
use App\Repository\EtapeDePreparationRepository;
use App\Repository\PlatRepository;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Process\Process;
use Symfony\Component\Routing\Annotation\Route;


class PlatController extends AbstractController
{
    /**
     * @Route("/ajouterplat", name="ajouterplat")
     */
    public function ajouterPlat(Request $request): Response
    {
        $plat =new Plat();
        $form =$this->createForm(PlatType::class,$plat);
        $form->handleRequest($request);//gere requette envoyer par l'utlisateur


        if($form->isSubmitted() && $form->isValid()){
            $plat->calculeNutritiments();
            $em=$this->getDoctrine()->getManager();
            $em->persist($plat);
            $em->flush();

            foreach ($plat->getTagNourriture() as $tag )
            {
                if ($tag->isVideo())
                {
                    $tag->getContenuMultimedia()->generatethumbnailtranscode480pmp4();
                }
            }

            $em->flush();
           return $this->redirectToRoute('afficherplat');
        }

        return $this->render("back/plat/ajouterplat.html.twig",
            [  'form' => $form->createView()]);
    }

    /**
     * @param PlatRepository $repo
     * @Route ("afficherplat",name="afficherplat")
     */
    public function afficher(PlatRepository $repo,PaginatorInterface $paginator,Request $request)
    {
        $donnees=$repo->findAll();

        $plat=$paginator->paginate(
            $donnees,
            /* query NOT result */
            $request->query->getInt('page', 1), /*numero de page en cours 1 par défaut*/
            7 /*limit per page*/
        );
        return $this->render("back/plat/afficherplat.html.twig",
            ["plat"=>$plat]

        );
    }


    /**
     * @param PlatRepository $repo
     * @Route ("afficherplatfront/{id}",name="afficherplatfront")
     */
    public function afficherfront(PlatRepository $repo,$id,PaginatorInterface $paginator,Request $request)
    {
       // $plats=$repo->findAll();
       // $plat=$repo->find($id);
        $donnees=$repo->findAll();
        $plat=$repo->find($id);

        $platss=$paginator->paginate(
            $donnees,
            /* query NOT result */
            $request->query->getInt('page', 1), /*numero de page en cours 1 par défaut*/
            7/*limit per page*/
        );
        return $this->render("front/plat/afficherplat.html.twig",
            ["platss"=>$platss,"plat"=>$plat]

        );
    }
    /**
     * @param PlatRepository $repo
     * @Route ("afficherplatfrontall",name="afficherplatfrontall")
     */
    public function afficherfrontall(PlatRepository $repo,PaginatorInterface $paginator,Request $request)
    {
        // $plats=$repo->findAll();
        // $plat=$repo->find($id);
        $donnees=$repo->findAll();

        $plats=$paginator->paginate(
            $donnees,
            /* query NOT result */
            $request->query->getInt('page', 1), /*numero de page en cours 1 par défaut*/
            7/*limit per page*/
        );
        return $this->render("front/plat/afficherallplat.html.twig",
            ["plats"=>$plats]

        );
    }

    /**
     * @param PlatRepository $repo
     * @param $id
     * @Route ("/supprimerplat/{id}",name="supprimerPlat")
     */
    function delete(PlatRepository $repo ,$id)
    {
        $em=$this->getDoctrine()->getManager()  ;
        $plat=$repo->find($id);
        $em->remove($plat);
        $em->flush();
        return $this->redirectToRoute('afficherplat');
    }


    /**
     * @Route("/modifierPlat/{id}", name="modifierPlat")
     */
    public function modifierPlat(Request $request,PlatRepository $repo,$id): Response
    {
        $plat =$repo->find($id);
        $form =$this->createForm(PlatType::class,$plat);
      //  $form->add("Ajouter",SubmitType::class);
        $form->handleRequest($request);//gere requette envoyer par l'utlisateur


        if($form->isSubmitted() && $form->isValid()){
            $plat->calculeNutritiments();
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            foreach ($plat->getTagNourriture() as $tag )
            {
                if ($tag->isVideo())
                {
                    $tag->getContenuMultimedia()->generatethumbnailtranscode480pmp4();
                }
            }
            $em->flush();
             return $this->redirectToRoute('afficherplat');
        }
        return $this->render("back/plat/ajouterplat.html.twig",
            [  'form' => $form->createView()]);
    }

    /**
     * @param CompositionRepository $repoC
     * @param EtapeDePreparationRepository $repoE
     * @param PlatRepository $repop
     * @Route ("afficherEtapeAliment/{id}",name="afficherEtapeAliment")
     */
    public function afficherEtapeAliment(CompositionRepository $repoC,$id,EtapeDePreparationRepository $repoE,PlatRepository  $repop)
    {

        $compostions= $repoC->findBy(['plat' => $id]);
        $etapeDePreparartion= $repoE->findBy(['plat' => $id]);
        $plat =$repop->find($id);

        return $this->render("back/plat/affichercompostionEtEtapes.html.twig",
            ["compostions"=>$compostions,"etapeDePreparation"=>$etapeDePreparartion,"plat"=>$plat]


        );


    }

    /**
     * @param PlatRepository $repository
     * @Route ("rechercheplat",name="rechercheplat")
     */
    public function Recherche(PlatRepository $repository,Request $request,PaginatorInterface $paginator)
    {

        $name=$request->get('searchplat');
        $donnees=$repository->findplatbyname($name);


        $plats=$paginator->paginate(
            $donnees,
            /* query NOT result */
            $request->query->getInt('page', 1), /*numero de page en cours 1 par défaut*/
            7/*limit per page*/
        );
        return $this->render("front/plat/afficherallplat.html.twig",
            ["plats"=>$plats]);

    }


}
