<?php

namespace App\Controller;

use App\Entity\ContenuMultimedia;
use App\Form\ContenuMultimediaType;
use App\Repository\ContenuMultimediaRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\HttpFoundation\File\File ;
use Vich\UploaderBundle\VichUploaderBundle;


class ContenueMutlimediaController extends AbstractController
{
    /**
     * @Route ("affichervideo\{url}\{extension}",name="affichervideo")
     */
    public function afficherVideo($url,$extension)
    {
        return $this->render("contenue_mutlimedia/video.html.twig",
            ["url"=>$url,"extension"=>$extension]);

    }

    /**
     * @Route ("ajouterfichier",name="api_ajouterfichierr")
     */
    public function  ajouterFile(Request $request)
     {
//        $contenuMultimedia = new ContenuMultimedia();
//
//        //if($request->isXmlHttpRequest()) {
//            // On instancie un nouvel article
//
//            // On décode les données envoyées
//            $donnees = json_decode($request->getContent());
//            // On hydrate l'objet
//            $contenuMultimedia->setFileMultimedia($donnees->file);
//
//             //On sauvegarde en base
//
//            $em=$this->getDoctrine()->getManager();
//            $em->persist($contenuMultimedia);
//            $em->flush();
//
//
//            // On retourne la confirmation
//            $response=  new Response($contenuMultimedia->getId());
//            $response->headers->set('Content-Type', 'text/html');
//            $response->setStatusCode($contenuMultimedia->getId());
//            return new Response("ok" , 201);
//                    return $this->render('contenue_mutlimedia/index.html.twig ' ,['form'=>$form->createView()]);
//
//       // }
//      //  return new Response("failedd" , 404);

//
         $contenuMultimedia = new ContenuMultimedia();
//        $file=new UploadedFile($path, "bacemayadi.jpg");
//        $contenuMultimedia->setFileMultimedia($file);
        $form = $this->createForm(ContenuMultimediaType::class, $contenuMultimedia);
        $form->add("Ajouter", SubmitType::class);
     //   dump($contenuMultimedia->getFileMultimedia());
      // $contenuMultimedia->setNomFile("test");
        $form->handleRequest($request);

      //  $form->submit();
       if ( $form->isSubmitted() )
        {


            $em=$this->getDoctrine()->getManager();
            $em->persist($contenuMultimedia);
            $em->flush();
            return $this->render('contenue_mutlimedia/responseAddjava.html.twig ' ,['id'=>$contenuMultimedia->getId()]);



        }
        return $this->render('contenue_mutlimedia/index.html.twig ' ,['form'=>$form->createView()]);

    }// for java

    /**
     * @Route ("deleteMultimedia/{id}",name="deletefichier")
     */
    public function  deleteFile(ContenuMultimediaRepository  $repository,$id)
    {
        $em=$this->getDoctrine()->getManager()  ;

        $contenuMultimedia = $repository->find($id);
        $em->remove($contenuMultimedia);
        $em->flush();
        return new Response("succes" , 200);
    }
}
