<?php

namespace App\Controller;

use App\Entity\Aliment;
use App\Entity\CategorieAliment;
use App\Form\AlimentType;
use App\Repository\AlimentRepository;
use App\Repository\CategorieAlimentRepository;
use phpDocumentor\Reflection\Types\String_;
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
    //    $s =new String_() ;


        $form =$this->createForm(AlimentType::class,$aliment);
        $form->add("Ajouter",SubmitType::class);
        $form->handleRequest($request);//gere requette envoyer par l'utlisateur

        if($form->isSubmitted() ){
            dump($form)  ;

            $em=$this->getDoctrine()->getManager();



            $em->persist($aliment);
            $em->flush();
           return $this->redirectToRoute('afficherAliment');
        }
        $categorie=$repo->findAll();
        return $this->render("back/aliment/ajouterAliment.html.twig",
            [ 'categorie' => $categorie , 'form'=> $form->createView(), ]);


    }

    /**
     * @param AlimentRepository $repo
     * @Route ("afficherAliment",name="afficherAliment")
     */
    public function afficher(AlimentRepository $repo)
    {
    $aliments=$repo->findAll();
    dump($aliments);
    return $this->render("back/aliment/afficherAliment.html.twig",
        ["aliments"=>$aliments]

    );
    }

    /**
     * @param AlimentRepository $repo
     * @param $id
     * @Route ("/supprimerAliment/{id}",name="supprimerAliment")
     */
    function delete(AlimentRepository $repo ,$id)
    {
        $em=$this->getDoctrine()->getManager()  ;
        $aliment=$repo->find($id);
        dump($aliment);
        $em->remove($aliment);
        $em->flush();
        return $this->redirectToRoute('afficherAliment');
    }
}
