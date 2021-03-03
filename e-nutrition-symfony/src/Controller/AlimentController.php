<?php

namespace App\Controller;

use App\Entity\Aliment;
use App\Entity\CategorieAliment;
use App\Form\AlimentType;
use App\Repository\CategorieAlimentRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class AlimentController extends AbstractController
{
    /**
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|Response
     * @Route ("/ajouteraliment",name="ajouterAliment")
     */
    public function ajoute(Request $request, CategorieAlimentRepository $repo)
    {
        $aliment =new Aliment();
        $form=$this->createForm(AlimentType::class,$aliment);

        $form->add("Ajouter",SubmitType::class);
        $form->handleRequest($request);//gere requette envoyer par l'utlisateur

        if($form->isSubmitted() ){

            $forms = $form->getData();
            dump([$forms])  ;

            $em=$this->getDoctrine()->getManager();
            //$forms = iterator_to_array($form);
            //dump([$form])  ;

          // $categories = $forms['categorieAliment']->getData(); ;
           // dump([$categories]);
        //    foreach ($categories as $categorie)
           //{
          // dump([$categorie])  ;
           //}


            $em->persist($aliment);
            $em->flush();
         //   return $this->redirectToRoute('studentAffiche');
        }
        $categorie=$repo->findAll();
        return $this->render("back/aliment/ajouterAliment.html.twig",
            ['categorie' => $categorie , 'form'=> $form->createView() ]);


    }
}
