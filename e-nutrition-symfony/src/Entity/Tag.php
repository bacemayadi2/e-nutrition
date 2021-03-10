<?php

namespace App\Entity;

use App\Repository\TagRepository;
use Doctrine\ORM\Mapping as ORM;
use phpDocumentor\Reflection\Types\Boolean;
use function Symfony\Component\String\u;

/**
 * @ORM\Entity(repositoryClass=TagRepository::class)
 * @ORM\InheritanceType("JOINED")
 */
class Tag
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\ManyToOne(targetEntity=ContenuMultimedia::class, inversedBy="tag",cascade={"all"})
     * @ORM\JoinColumn(nullable=false)
     */
    private $contenuMultimedia;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getContenuMultimedia(): ?ContenuMultimedia
    {
        return $this->contenuMultimedia;
    }

    public function setContenuMultimedia(?ContenuMultimedia $contenuMultimedia): self
    {
        $this->contenuMultimedia = $contenuMultimedia;
        $contenuMultimedia->addTag($this);

        return $this;
    }

    public function getExtension():?string
    {
        $url=$this->getContenuMultimedia()->getNomFile();
        if ($url != null)
        {
       $extension =(u($url)->split('.'))[1];
       return $extension;

        }
        return "null";
    }
    public function getUrl(): ?string
    {
       return 'multimedia/' . $this->getContenuMultimedia()->getNomFile();
    }

    public function isImage():?bool
    {
        dump($this->getExtension());
        return (in_array($this->getExtension() , ["jpg","png","png"]) );

    }
    public function isVideo():?bool
    {
        return (in_array($this->getExtension() ,["mp4","mkv","mpg","avi"]));

    }



}
