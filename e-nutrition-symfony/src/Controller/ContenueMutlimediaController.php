<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

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


}
