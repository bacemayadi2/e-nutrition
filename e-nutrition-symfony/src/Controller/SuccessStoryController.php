<?php

namespace App\Controller;

use App\Entity\RendezVous;
use App\Entity\SuccessStory;
use App\Entity\TagSuccessStory;
use App\Form\CommentsType;
use App\Form\RendezVousType;
use App\Form\SuccessStoryType;
use App\Repository\CommentsRepository;
use App\Repository\SuccessStoryRepository;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Comments;


class SuccessStoryController extends AbstractController
{

    /**
     * @Route("/success/story", name="success_story")
     */
    public function index(): Response
    {
        return $this->render('success_story/afficherMedicament.html.twig', [
            'controller_name' => 'SuccessStoryController',
        ]);
    }

    /**
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|Response
     * @Route ("CreateSuccess", name="Succ")
     */

    function CreateSuccess(Request $request)
    {
        $Success = new SuccessStory();
        $time = new \DateTime();
        $Success->setDateCreation($time);
        $image1 = new TagSuccessStory();
        $image2 = new TagSuccessStory();
        $video = new TagSuccessStory();
        $Success ->addTagSucess($image1);
        $Success ->addTagSucess($image2);
        $Success ->addTagSucess($video);
        $form = $this->createForm(SuccessStoryType::class, $Success);
        $form->add("Ajouter", SubmitType::class);


        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid())
        {
            $em = $this->getDoctrine()->getManager();
            $em->persist($Success);
            $em->flush();
            $this->addFlash(
                'notice',
                'Your changes were saved!'
            );

            return $this->redirectToRoute('Pub');
        }
        return $this->render('success_story/Success.html.twig ' ,['form'=>$form->createView()]);
    }





    /**
     * @param SuccessStoryRepository $repository
     * @return Response
     * @Route("Success", name="Pub")
     */
    public function AfficherSuccess(SuccessStoryRepository $repository ){

        //$repo= $this->getDoctrine()->getRepository(RendezVous::class   );
        $Success=$repository->findAll();

        return $this->render('success_story/public.html.twig', [
            'controller_name' => 'SuccessStoryController',
            'Success' => $Success,

        ]);
    }
    /**
     * @param SuccessStoryRepository $repository
     * @return Response
     * @Route("detpost/{id}", name="postSuccess")
     */
    public function AfficherPostSuccess(CommentsRepository $rep ,SuccessStoryRepository $repository , $id ,Request  $request){

        //$repo= $this->getDoctrine()->getRepository(RendezVous::class   );
        $Success=$repository->find($id);
        $comments= $rep->findBy(['success' => $id]);
        //$time = new \DateTime();
        //partie commentaire//

        $comment = new Comments();

        $commentForm = $this->createForm(CommentsType::class , $comment);
        $commentForm -> handleRequest($request);

        //traitement formulaire
        if ($commentForm->isSubmitted() && $commentForm->isValid())
        {
            $comment->setSuccess($Success);
            $comment->setDateAt(new \DateTime());

            //recuperation champ parentid
            $parentid = $commentForm->get("parentid")->getData();


            $em = $this->getDoctrine()->getManager();
            if ($parentid != null) {
                $parent = $em->getRepository(Comments::class)->find($parentid);
            }
            //on definit le parent
            $comment->setParent($parent ?? null);
            $em->persist($comment);
            $em->flush();



            return $this->redirectToRoute('postSuccess' , ['id'=> $id]);
        }



        return $this->render('success_story/detpost.html.twig', [
            'controller_name' => 'SuccessStoryController',
            'Success' => $Success,
            'commentform'=> $commentForm->createView(),
            'comments'=>$comments

        ]);
    }
    /**
     * @param SuccessStoryRepository $repository
     * @return Response
     * @Route("SuccessBack", name="PubBack")
     */
    public function AfficherSuccessBack(PaginatorInterface $paginator , SuccessStoryRepository $repository , Request $request ){

        //$repo= $this->getDoctrine()->getRepository(RendezVous::class   );
        $donnees=$repository->findAll();
        $Success=$paginator->paginate(
            $donnees,
            /* query NOT result */
            $request->query->getInt('page', 1), /*numero de page en cours 1 par défaut*/
            7 /*limit per page*/
        );

        return $this->render('back/SuccessStory/SuccessAdmin.html.twig', [
            'controller_name' => 'SuccessStoryController',
            'Success' => $Success,
        ]);
    }

    /**
     * @param $id
     * @param SuccessStoryRepository $repository
     * @return \Symfony\Component\HttpFoundation\RedirectResponse
     * @Route("deleteSuccess/{id}", name="deleteSuccess")
     */
    public function Delete($id , SuccessStoryRepository $repository){
        $Success=$repository->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($Success);
        $em->flush();
        return $this->redirectToRoute('Pub');
    }

}
