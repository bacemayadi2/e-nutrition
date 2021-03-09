<?php

namespace App\Entity;

use App\Repository\TagRepository;
use Doctrine\ORM\Mapping as ORM;

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

    public function getUrl(): ?string
    {
       return 'multimedia/' . $this->getContenuMultimedia()->getNomFile();
    }


}
