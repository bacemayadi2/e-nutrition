<?php

namespace App\MessageHandler;

use App\Controller\ContenueMutlimediaController;
use App\Entity\ContenuMultimedia;
use App\Entity\Tag;
use App\Message\EditingUploadedVideo;
use App\Repository\ContenuMultimediaRepository;
use FFMpeg\Coordinate\Dimension;
use FFMpeg\Coordinate\TimeCode;
use FFMpeg\FFMpeg;
use FFMpeg\Format\Video\X264;
use Symfony\Component\Filesystem\Filesystem;
use Symfony\Component\Messenger\Handler\MessageHandlerInterface;
use Vich\UploaderBundle\Event\Event;
use function Symfony\Component\String\u;

final class EditingUploadedVideoMessageHandler implements MessageHandlerInterface
{



    public function __invoke(EditingUploadedVideo $message)
    {
        $contenucontroleur=new ContenueMutlimediaController( );
        $projectDir=$message->getDir();
        $nomfile= $message->getFilename();
        ContenuMultimedia::generatethumbnailtranscode480pmp4($nomfile,$projectDir);

        }
}
