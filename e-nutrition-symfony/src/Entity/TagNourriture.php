<?php

namespace App\Entity;

use App\Repository\TagNourritureRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=TagNourritureRepository::class)
 */
class TagNourriture extends Tag
{
    /**
     * @ORM\ManyToOne(targetEntity=Nourriture::class, inversedBy="tagNourriture",cascade={"all"})
     * @ORM\JoinColumn(nullable=false)
     */

    private $nourriture;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $test;

    public function getNourriture(): ?Nourriture
    {
        return $this->nourriture;
    }

    public function setNourriture(?Nourriture $nourriture): self
    {
        $this->nourriture = $nourriture;

        return $this;
    }

    public function getTest(): ?string
    {
        return $this->test;
    }

    public function setTest(?string $test): self
    {
        $this->test = $test;

        return $this;
    }
}
