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



    public function getNourriture(): ?Nourriture
    {
        return $this->nourriture;
    }

    public function setNourriture(?Nourriture $nourriture): self
    {
        $this->nourriture = $nourriture;

        return $this;
    }





}
