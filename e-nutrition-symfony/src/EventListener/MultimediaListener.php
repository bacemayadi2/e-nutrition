<?php


namespace App\EventListener;

use Symfony\Component\Filesystem\Filesystem;
use Vich\UploaderBundle\Event\Event;
use function Symfony\Component\String\u;


class MultimediaListener
{
public function onVichuploaderPreremove(Event $event){
    $object= $event->getObject();
    $mapping= $event->getMapping();
    $filename = $mapping->getFileName($object);
    $extension =(u($filename)->split('.'))[1];
    $name=(u($filename)->split('.'))[0];
    if ($extension=="mp4")
    {
        $fileSystem= new Filesystem();
        $fileSystem->remove('multimedia/'.$name .'.jpg'); // remove old file
        dump('multimedia/'.$name .'.jpg');
    }




}
}