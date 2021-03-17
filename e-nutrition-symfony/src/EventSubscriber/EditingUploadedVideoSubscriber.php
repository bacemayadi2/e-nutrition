<?php

namespace App\EventSubscriber;

use App\Controller\ContenueMutlimediaController;
use App\Entity\ContenuMultimedia;
use App\Entity\Tag;
use App\Message\EditingUploadedVideo;
use Symfony\Component\DependencyInjection\ParameterBag\ParameterBagInterface;
use Symfony\Component\EventDispatcher\EventSubscriberInterface;
use Symfony\Component\Messenger\MessageBusInterface;
use Vich\UploaderBundle\Event\Event;
use Vich\UploaderBundle\Event\Events;



class EditingUploadedVideoSubscriber implements EventSubscriberInterface
{
    private $messageBus;

    public function __construct(MessageBusInterface $messageBus,ParameterBagInterface $parameterBag)
    {
        $this->messageBus = $messageBus;
        $this->parameterBag= $parameterBag;

    }



    public static function getSubscribedEvents()
    {
        return [
            Events::POST_UPLOAD => ['onVichUploaderPostUpload'],

        ];
    }


    public function onVichUploaderPostUpload(Event $event)
    {

        $object= $event->getObject();
        $mapping= $event->getMapping();
        $tag=new Tag();
        $tag->setContenuMultimedia(new ContenuMultimedia());
        $filename = $mapping->getFileName($object);
        $tag->getContenuMultimedia()->setNomFile($filename);
        $projectDir=$this->parameterBag->get('kernel.project_dir').'\\public\\';

        if  ($tag->isVideo()) {
            $mapping->setFileName($object,$tag->getName() . 'new' . '.mp4');
            $this->dispatch(EditingUploadedVideo::class, $filename,$projectDir);
        }

    }

    private function dispatch(string $messageClass, string  $filename,string $projectDir): void
    {

        $message = new $messageClass($filename,$projectDir);

        $this->messageBus->dispatch($message);
    }

}
