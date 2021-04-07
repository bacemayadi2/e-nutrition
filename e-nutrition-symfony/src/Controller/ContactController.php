<?php
//
//namespace App\Controller;
//
//use http\Env\Request;
//use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
//use Symfony\Component\HttpFoundation\Response;
//use Symfony\Component\Routing\Annotation\Route;
//use App\Form\ContactType;
//
//
//class ContactController extends AbstractController
//{
//    /**
//     * @Route("/contact", name="contact")
//     */
//    public function index(Request $request): Response
//    {
//        $form = $this->createForm(ContactType::class);
//        $form->handleRequest($request);
//
//        if ($form->isSubmitted() && $form->isValid()) {
//            $contact = $form->getData();
//
//            // Ici nous enverrons l'e-mail
//
//           dd($contact);
//        }
//        return $this->render('contact/index.html.twig', [
//            'ContactForm' => $form-> createView(),
//        ]);
//    }
//}


namespace App\Controller;

use App\Form\ContactType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;

class ContactController extends AbstractController
{
    /**
     * @Route("/contact", name="contact")
     */
    public function index(Request $request , \Swift_Mailer $mailer)
    {
        $form = $this->createForm(ContactType::class);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $contact = $form->getData();

            // Ici nous enverrons l'e-mail
            //$this->addFlash('message', 'Votre message a été transmis, nous vous répondrons dans les meilleurs délais.'); // Permet un message flash de renvoi
            // On crée le message
            $message = (new \Swift_Message('Nouveau contact'))
                // On attribue l'expéditeur
                ->setFrom($contact['email'])
                // On attribue le destinataire
                ->setTo('votre@adresse.com')
                // On crée le texte avec la vue
                ->setBody(
                    $this->renderView(
                        'email/contact.html.twig', compact('contact')
                    ),
                    'text/html'
                )
            ;
            //to send message
            $mailer->send($message);
            // Permet un message flash de renvoi
            $this->addFlash('message', 'Votre message a été transmis, nous vous répondrons dans les meilleurs délais.');
            return $this-> redirectToRoute('contact');

        }
        return $this->render('contact/index.html.twig', ['contactForm' => $form->createView()]);
    }

}