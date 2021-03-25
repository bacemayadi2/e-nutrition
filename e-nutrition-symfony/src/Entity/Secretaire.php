<?php

namespace App\Entity;

use App\Repository\SecretaireRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=SecretaireRepository::class)
 */
class Secretaire extends Utilisateur
{
    /**
     * @ORM\ManyToOne(targetEntity=Nutritionniste::class, inversedBy="secretaire")
     */
    private $nutritionniste;


    public function getNutritionniste(): ?Nutritionniste
    {
        return $this->nutritionniste;
    }

    public function setNutritionniste(?Nutritionniste $nutritionniste): self
    {
        $this->nutritionniste = $nutritionniste;
        return $this;
    }

    public function __toString() {
        // to show the name of the Category in the select
        return $this->getNom() + $this->getPrenom() ;
    }
}
