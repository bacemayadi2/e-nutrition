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

    /**
     * @ORM\OneToMany(targetEntity=FicheConsultation::class, mappedBy="nutritionniste",cascade={"all"},orphanRemoval=true)
     */
    private $ficheConsultations;

    /**
     * @ORM\OneToMany(targetEntity=Evaluation::class, mappedBy="nutritionniste")
     */
    private $evaluations;

    public function __construct()
    {
        $this->secretaire = new ArrayCollection();
        $this->ficheConsultations = new ArrayCollection();
        $this->evaluations = new ArrayCollection();
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

    /**
     * @return Collection|FicheConsultation[]
     */
    public function getFicheConsultations(): Collection
    {
        return $this->ficheConsultations;
    }

    public function addFicheConsultation(FicheConsultation $ficheConsultation): self
    {
        if (!$this->ficheConsultations->contains($ficheConsultation)) {
            $this->ficheConsultations[] = $ficheConsultation;
            $ficheConsultation->setNutritionniste($this);
        }

        return $this;
    }

    public function removeFicheConsultation(FicheConsultation $ficheConsultation): self
    {
        if ($this->ficheConsultations->removeElement($ficheConsultation)) {
            // set the owning side to null (unless already changed)
            if ($ficheConsultation->getNutritionniste() === $this) {
                $ficheConsultation->setNutritionniste(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|Evaluation[]
     */
    public function getEvaluations(): Collection
    {
        return $this->evaluations;
    }

    public function addEvaluation(Evaluation $evaluation): self
    {
        if (!$this->evaluations->contains($evaluation)) {
            $this->evaluations[] = $evaluation;
            $evaluation->setNutritionniste($this);
        }

        return $this;
    }

    public function removeEvaluation(Evaluation $evaluation): self
    {
        if ($this->evaluations->removeElement($evaluation)) {
            // set the owning side to null (unless already changed)
            if ($evaluation->getNutritionniste() === $this) {
                $evaluation->setNutritionniste(null);
            }
        }

        return $this;
    }

}
