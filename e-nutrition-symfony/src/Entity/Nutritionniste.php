<?php

namespace App\Entity;

use App\Repository\NutritionnisteRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=NutritionnisteRepository::class)
 */
class Nutritionniste extends Utilisateur
{
    /**
     * @ORM\OneToMany(targetEntity=Secretaire::class, mappedBy="nutritionniste")
     */
    private $secretaire;

    public function __construct()
    {
        $this->secretaire = new ArrayCollection();
    }

    /**
     * @return Collection|Secretaire[]
     */
    public function getSecretaire(): Collection
    {
        return $this->secretaire;
    }

    public function addSecretaire(Secretaire $secretaire): self
    {
        if (!$this->secretaire->contains($secretaire)) {
            $this->secretaire[] = $secretaire;
            $secretaire->setNutritionniste($this);
        }

        return $this;
    }

    public function removeSecretaire(Secretaire $secretaire): self
    {
        if ($this->secretaire->removeElement($secretaire)) {
            // set the owning side to null (unless already changed)
            if ($secretaire->getNutritionniste() === $this) {
                $secretaire->setNutritionniste(null);
            }
        }

        return $this;
    }
}
