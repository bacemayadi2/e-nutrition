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

    /**
     * @ORM\OneToMany(targetEntity=Mesure::class, mappedBy="patient", orphanRemoval=true)
     */
    private $mesures;

    /**
     * @ORM\OneToMany(targetEntity=Proportion::class, mappedBy="patient")
     */
    private $proportions;

    /**
     * @ORM\ManyToMany(targetEntity=Challenge::class, inversedBy="participants")
     */
    private $challenges;



    public function __construct()
    {
        $this->ficheConsultations = new ArrayCollection();
        $this->evaluations = new ArrayCollection();
        $this->mesures = new ArrayCollection();
        $this->proportions = new ArrayCollection();
        $this->challenges = new ArrayCollection();
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

    /**
     * @return Collection|Mesure[]
     */
    public function getMesures(): Collection
    {
        return $this->mesures;
    }

    public function addMesure(Mesure $mesure): self
    {
        if (!$this->mesures->contains($mesure)) {
            $this->mesures[] = $mesure;
            $mesure->setPatient($this);
        }

        return $this;
    }

    public function removeMesure(Mesure $mesure): self
    {
        if ($this->mesures->removeElement($mesure)) {
            // set the owning side to null (unless already changed)
            if ($mesure->getPatient() === $this) {
                $mesure->setPatient(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|Challenge[]
     */
    public function getChallenges(): Collection
    {
        return $this->challenges;
    }

    public function addChallenge(Challenge $challenge): self
    {
        if (!$this->challenges->contains($challenge)) {
            $this->challenges[] = $challenge;
        }

        return $this;
    }

    public function removeChallenge(Challenge $challenge): self
    {
        $this->challenges->removeElement($challenge);

        return $this;
    }

    /**
     * @return Collection|Proportion[]
     */
    public function getProportions(): Collection
    {
        return $this->proportions;
    }

    public function addProportion(Proportion $proportion): self
    {
        if (!$this->proportions->contains($proportion)) {
            $this->proportions[] = $proportion;
            $proportion->setPatient($this);
        }

        return $this;
    }

    public function removeProportion(Proportion $proportion): self
    {
        if ($this->proportions->removeElement($proportion)) {
            // set the owning side to null (unless already changed)
            if ($proportion->getPatient() === $this) {
                $proportion->setPatient(null);
            }
        }

        return $this;
    }

}
