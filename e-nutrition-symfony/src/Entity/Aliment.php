<?php

namespace App\Entity;

use App\Repository\AlimentRepository;
use Doctrine\ORM\Mapping as ORM;


/**
 * @ORM\Entity(repositoryClass=AlimentRepository::class)
 */
class Aliment Extends Nourriture
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $codeABarre;

    /**
     * @ORM\ManyToOne(targetEntity=CategorieAliment::class, inversedBy="aliments")
     * @ORM\JoinColumn(nullable=false)
     */
    private $categorieAliment;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getCodeABarre(): ?string
    {
        return $this->codeABarre;
    }

    public function setCodeABarre(?string $codeABarre): self
    {
        $this->codeABarre = $codeABarre;

        return $this;
    }

    public function getCategorieAliment(): ?CategorieAliment
    {
        return $this->categorieAliment;
    }

    public function setCategorieAliment(?CategorieAliment $categorieAliment): self
    {
        $this->categorieAliment = $categorieAliment;

        return $this;
    }
}
