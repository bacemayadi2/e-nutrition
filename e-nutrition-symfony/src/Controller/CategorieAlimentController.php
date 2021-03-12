<?php

namespace App\Controller;

use App\Entity\CategorieAliment;
use App\Form\CategorieAlimentType;
use App\Repository\CategorieAlimentRepository;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class CategorieAlimentController extends AbstractController
{
    /**
     * @Route("/categorie/aliment", name="categorie_aliment")
     */
    public function index(): Response
    {
        return $this->render('categorie_aliment/index.html.twig', [
            'controller_name' => 'CategorieAlimentController',
        ]);
    }
    /**
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|Response
     * @Route ("ajoutercategorie",name="ajouterCategorie")
     */
    public function ajoute(Request $request)
    {
        $categorie =new CategorieAliment();
        $form =$this->createForm(CategorieAlimentType::class,$categorie);
        $form->handleRequest($request);//gere requette envoyer par l'utlisateur
        if($form->isSubmitted())

        if($form->isSubmitted() && $form->isValid()){
            $em=$this->getDoctrine()->getManager();

            $em->persist($categorie);
            $em->flush();
            return $this->redirectToRoute('afficherCategorie');
        }
        return $this->render("back/categorie_aliment/ajouterCategorie.html.twig",
            [  'form'=> $form->createView() ]);
    }

    /**
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|Response
     * @Route ("modifercategorie/{id}",name="modiferCategorie")
     */
    public function modifer(Request $request,CategorieAlimentRepository $repo,$id)
    {
        $categorie =$repo->find($id);
        $form =$this->createForm(CategorieAlimentType::class,$categorie);
        $form->handleRequest($request);//gere requette envoyer par l'utlisateur
        if($form->isSubmitted())

            if($form->isSubmitted() && $form->isValid()){
                $em=$this->getDoctrine()->getManager();

                $em->flush();
                return $this->redirectToRoute('afficherCategorie');
            }
        return $this->render("back/categorie_aliment/ajouterCategorie.html.twig",
            [  'form'=> $form->createView() ]);
    }

    /**
     * @Route ("afficherCategorie",name="afficherCategorie")
     */
    public function afficher(CategorieAlimentRepository $repo,PaginatorInterface $paginator,Request $request)
    {
        $donnees=$repo->findAll();
        $categories=$paginator->paginate(
            $donnees,
            /* query NOT result */
            $request->query->getInt('page', 1), /*numero de page en cours 1 par défaut*/
            7 /*limit per page*/
        );
        return $this->render("back/categorie_aliment/afficherCategorie.html.twig",
            ["categories"=>$categories]);
    }
    /**
     * @param CategorieAlimentRepository $repo
     * @param $id
     * @Route ("supprimerCategorie/{id}",name="supprimerCategorie")
     */
    function delete(CategorieAlimentRepository $repo ,$id)
    {
        $em=$this->getDoctrine()->getManager()  ;
        $categorie=$repo->find($id);
        $em->remove($categorie);
        $em->flush();
        return $this->redirectToRoute('afficherCategorie');
    }
}
