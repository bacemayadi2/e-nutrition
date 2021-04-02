<?php

namespace App\Controller;

use App\Entity\Mesure;
use App\Form\Mesure1Type;
use App\Repository\MesureRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/mesure")
 */
class MesureController extends AbstractController
{
    /**
     * @Route("/", name="mesure_index", methods={"GET"})
     */
    public function index(MesureRepository $mesureRepository): Response
    {
        return $this->render('back/mesure/index.html.twig', [
            'mesures' => $mesureRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="mesure_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $mesure = new Mesure();
        $form = $this->createForm(Mesure1Type::class, $mesure);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $mesure->setDateMesure(new \DateTime());
            $mesure->setPatient($this->getUser());
            $entityManager->persist($mesure);
            $entityManager->flush();

            return $this->redirectToRoute('mesure_index');
        }

        return $this->render('back/mesure/edit_mesure.html.twig', [
            'mesure' => $mesure,
            'form' => $form->createView(),
            'action' => $this->generateUrl("mesure_new")
        ]);
    }

    /**
     * @Route("/show", name="mesure_show", methods={"GET"})
     */

    public function show(MesureRepository $repo): Response
    {
        $mesure=$repo->findAll();
        return $this->render('mesure/show.html.twig', [
            'mesure' => $mesure,
        ]);
    }

    /**

     * @Route("/showf", name="mesure_show", methods={"GET"})
     */
    public function showf(MesureRepository $repo): Response
    {
        $mesure=$repo->findAll();
        return $this->render('front/mesure/affichage.html.twig', [
            'mesure' => $mesure,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="mesure_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Mesure $mesure): Response
    {
        $form = $this->createForm(Mesure1Type::class, $mesure);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('mesure_index');
        }

        return $this->render('back/mesure/edit_mesure.html.twig', [
            'mesure' => $mesure,
            'form' => $form->createView(),
            'action' => $this->generateUrl("mesure_edit", ["id"=>$mesure->getId()]) ]
        );
    }

    /**
     * @Route("delete/{id}", name="mesure_delete")
     */
    public function delete(Request $request, MesureRepository $repo,$id): Response
    {
        $em=$this->getDoctrine()->getManager()  ;
        $mesure=$repo->find($id);
        $em->remove($mesure);
        $em->flush();
//        if ($this->isCsrfTokenValid('delete'.$mesure->getId(), $request->request->get('_token'))) {
//            $entityManager = $this->getDoctrine()->getManager();
//            $entityManager->remove($mesure);
//            $entityManager->flush();
//        }

        return $this->redirectToRoute('mesure_index');
    }
}
