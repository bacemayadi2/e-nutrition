<?php

namespace App\Entity;

use App\Repository\TagRepository;
use Doctrine\ORM\Mapping as ORM;
use phpDocumentor\Reflection\Types\Boolean;
use phpDocumentor\Reflection\Types\Null_;
use phpDocumentor\Reflection\Types\String_;
use function Symfony\Component\String\u;
use Symfony\Component\Serializer\Annotation\Groups as Groups;


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
     * @Groups ("plat:read")
     * @Groups ("challenges:read")
     * @Groups("fiche")
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

    public function getName():?string
    {
        $url=$this->getContenuMultimedia()->getNomFile();
        if ($url != null)
        {
            $name =(u($url)->split('.'))[0];
            return $name;

        }
        return "null";
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
        return (in_array($this->getExtension() , ["jpg","png","JPG","PNG"]) );

    }
    public function isVideo():?bool
    {
        return (in_array($this->getExtension() ,["mp4","mkv","mpg","avi","mov","MOV"]));

    }

    public function getthumbnail():String
    {
        return ('multimedia/'.$this->getName().'.jpg');
    }




}
