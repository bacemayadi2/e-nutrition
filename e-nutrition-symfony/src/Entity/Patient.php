<?php

namespace App\Entity;

use App\Repository\PatientRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=PatientRepository::class)
 */
class Patient extends Utilisateur
{
    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="saisir votre style de vie svp !!!")
     */
    private $styleDeVie;

    /**
     * @ORM\OneToMany(targetEntity=FicheConsultation::class, mappedBy="patient",cascade={"all"},orphanRemoval=true)
     */
    private $ficheConsultations;

    /**
     * @ORM\OneToMany(targetEntity=Evaluation::class, mappedBy="patient")
     */
    private $evaluations;

    public function __construct()
    {
        $this->ficheConsultations = new ArrayCollection();
        $this->evaluations = new ArrayCollection();
    }

    public function getStyleDeVie(): ?string
    {
        return $this->styleDeVie;
    }

    public function setStyleDeVie(string $styleDeVie): self
    {
        $this->styleDeVie = $styleDeVie;
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
            $ficheConsultation->setPatient($this);
        }

        return $this;
    }

    public function removeFicheConsultation(FicheConsultation $ficheConsultation): self
    {
        if ($this->ficheConsultations->removeElement($ficheConsultation)) {
            // set the owning side to null (unless already changed)
            if ($ficheConsultation->getPatient() === $this) {
                $ficheConsultation->setPatient(null);
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
            $evaluation->setPatient($this);
        }

        return $this;
    }

    public function removeEvaluation(Evaluation $evaluation): self
    {
        if ($this->evaluations->removeElement($evaluation)) {
            // set the owning side to null (unless already changed)
            if ($evaluation->getPatient() === $this) {
                $evaluation->setPatient(null);
            }
        }

        return $this;
    }









}
