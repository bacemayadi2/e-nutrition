<?php

namespace App\Controller;

use App\Entity\BlogPost;
use App\Form\BlogPostType;
use App\Repository\BlogPostRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

/**
 * @Route("/blog/post")
 */
class BlogPostController extends AbstractController
{
    /**
     * @Route("/", name="blog_post_index", methods={"GET"})
     */
    public function index(BlogPostRepository $blogPostRepository): Response
    {
        $blogPosts = $blogPostRepository->findAll();
        return $this->render('front/blog/index.html.twig', [
            'blog_posts' => $blogPosts,
            'truncate' => true
        ]);
    }

    /**
     * @Route("/admin/show_all", name="blog_post_index_admin", methods={"GET"})
     */
    public function back_index(BlogPostRepository $blogPostRepository): Response
    {
        $blogPosts = $blogPostRepository->findAll();
        return $this->render('back/blog/index.html.twig', [
            'blog_posts' => $blogPosts,
        ]);
    }

    /**
     * @Route("/new", name="blog_post_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $blogPost = new BlogPost();
        $form = $this->createForm(BlogPostType::class, $blogPost);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $blogPost->setDate(new \DateTime());
            $entityManager->persist($blogPost);
            $entityManager->flush();

            return $this->redirectToRoute('blog_post_index_admin');
        }

        return $this->render("back/blog/edit_blogpost.html.twig", [
            'blog_post' => $blogPost,
            'form' => $form->createView(),
            'action' => $this->generateUrl("blog_post_new")
        ]);
    }

    /**
     * @Route("/{id}", name="blog_post_show", methods={"GET"})
     */
    public function show(BlogPost $blogPost): Response
    {
        return $this->render('front/blog/index.html.twig', [
            'blog_posts' => [$blogPost],
            'truncate' => false
        ]);
    }

    /**
     * @Route("/{id}/edit", name="blog_post_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, BlogPost $blogPost): Response
    {
        $form = $this->createForm(BlogPostType::class, $blogPost);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();
            return $this->redirectToRoute('blog_post_index_admin');
        }

        return $this->render('back/blog/edit_blogpost.html.twig', [
            'blog_post' => $blogPost,
            'form' => $form->createView(),
            'action' => $this->generateUrl("blog_post_edit", ['id' => $blogPost->getId()])
        ]);
    }

    /**
     * @Route("/{id}/delete", name="blog_post_delete", methods={"GET"})
     */
    public function delete(Request $request, BlogPost $blogPost): Response
    {
        //if ($this->isCsrfTokenValid('delete'.$blogPost->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($blogPost);
            $entityManager->flush();
        //}

        return $this->redirectToRoute('blog_post_index_admin');
    }

    /**
     * @Route("/search", name="blogsearch")
     */
    public function Recherche(BlogPostRepository $blogPostRepository ,Request  $request,NormalizerInterface $normalizer)
    {
        $blogPosts = $blogPostRepository->findAll();
        return $this->render('front/blog/index.html.twig', [
            'blog_posts' => $blogPosts,
            'truncate' => true
        ]);

//        $aliment=$paginator->paginate(
//            $donnees,,
//            /* query NOT result */
//            $request->query->getInt('page', 1), /*numero de page en cours 1 par défaut*/
//            7/*limit per page*/
//        );
//        return $this->render("back/aliment/afficherAliment.html.twig",
//            ["aliment"=>$aliment] );

    //    $repository = $this->getDoctrine()->getRepository(BlogPost::class);
      //  $title=$request->get('searchValue');


     //   $blog=$repository->findBlogPostbyname($title);
//        $aliment=$paginator->paginate(
//            $donnees,
//            /* query NOT result */
//            $request->query->getInt('page', 1), /*numero de page en cours 1 par défaut*/
//            7/*limit per page*/
//        );
       //dump($blog);
       // $jsonContent = $normalizer->normalize($blog, 'json',['groups'=>'blogs']);
  //      $retour =json_encode($jsonContent);
    //    return new Response($retour);
    }
}
