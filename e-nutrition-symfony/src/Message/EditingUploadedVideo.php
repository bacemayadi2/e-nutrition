<?php

namespace App\Message;
use Vich\UploaderBundle\Event\Event;


final class EditingUploadedVideo
{
   private $filename ;
   private $dir;

    public function __construct(string $filename , string $dir )
    {
        $this->filename = $filename;
        $this->dir = $dir;
    }

    /**
     * @return string
     */
    public function getDir(): string
    {
        return $this->dir;
    }

    /**
     * @return string
     */
    public function getFilename(): string
    {
        return $this->filename;
    }


    /*
     * Add whatever properties & methods you need to hold the
     * data for this message class.
     */

//     private $name;
//
//     public function __construct(string $name)
//     {
//         $this->name = $name;
//     }
//
//    public function getName(): string
//    {
//        return $this->name;
//    }
}
