<?php


namespace App\EventListener;

use App\Entity\ContenuMultimedia;
use App\Entity\Tag;
use FFMpeg\Coordinate\Dimension;
use FFMpeg\Coordinate\TimeCode;
use FFMpeg\FFMpeg;
use FFMpeg\Format\Video\X264;
use Symfony\Component\Filesystem\Filesystem;
use Symfony\Component\Messenger\MessageBusInterface;
use Vich\UploaderBundle\Event\Event;
use function Symfony\Component\String\u;


class MultimediaListener
{
    private $messageBus;

    public function __construct(MessageBusInterface $messageBus)
    {
        $this->messageBus = $messageBus;
    }


public function onVichuploaderPreremove(Event $event){
    $object= $event->getObject();
    $mapping= $event->getMapping();

    $filename = $mapping->getFileName($object);
    $tag=new Tag();
    $tag->setContenuMultimedia(new ContenuMultimedia());
    $tag->getContenuMultimedia()->setNomFile($filename);

    if ($tag->isVideo())
    {
        $fileSystem= new Filesystem();
        $fileSystem->remove('multimedia/'.$tag->getName() .'.jpg'); // remove old file
        $fileSystem->remove('multimedia/'.$tag->getName() .'480.mp4');
        $fileSystem->remove('multimedia/'.$tag->getName() .'240.mp4');
        $fileSystem->remove('multimedia/'.$tag->getName() .'360.mp4');

    }



}

public function onVichuploaderPostupload(Event $event)
{

 //   $this->messageBus->dispatch($event);

}
//    public function generatethumbnailtranscode480pmp4($filename):bool
//    {
//
//        $name = u($filename)->split('.')[0];
//        if (!(u($name)->containsAny("new"))) {
//            $logger = null;
//            $ffmpeg = FFMpeg::create(array(
//                'ffmpeg.binaries' => 'ffmpeg.exe',
//                'ffprobe.binaries' => 'ffprobe.exe',
//                'timeout' => 99999999, // The timeout for the underlying process
//                'ffmpeg.threads' => 20,   // The number of threads that FFMpeg should use
//            ), $logger);
//            $format = new X264();
//            $format->setAudioCodec("libmp3lame");
//            $video = $ffmpeg->open('multimedia/' . $filename);
//            $video
//                ->filters()
//                ->resize(new Dimension(848, 480));
//            $video
//                ->frame(TimeCode::fromSeconds(10))
//                ->save('multimedia/' . $name . 'new' . '.jpg');
//
//            $video
//                ->save($format, 'multimedia/' . $name . 'new' . '.mp4');
//
//            $fileSystem= new Filesystem();
//            $fileSystem->remove('multimedia/' . $filename); // remove old file
//
//            return true;
//        }
//        return false;
//
//    }
}