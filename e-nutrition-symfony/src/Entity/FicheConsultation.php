<?php

namespace App\Entity;

use App\Repository\FicheConsultationRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=FicheConsultationRepository::class)
 */
class FicheConsultation
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="date")
     */
    private $CreationDate;

    /**
     * @ORM\Column(type="float")
     */
    private $Poids;

    /**
     * @ORM\Column(type="float")
     */
    private $Taille;

    /**
     * @ORM\Column(type="float")
     */
    private $Imc;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $Symptome;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $Apetit;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $Description;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getCreationDate(): ?\DateTimeInterface
    {
        return $this->CreationDate;
    }

    public function setCreationDate(\DateTimeInterface $CreationDate): self
    {
        $this->CreationDate = $CreationDate;

        return $this;
    }

    public function getPoids(): ?float
    {
        return $this->Poids;
    }

    public function setPoids(float $Poids): self
    {
        $this->Poids = $Poids;

        return $this;
    }

    public function getTaille(): ?float
    {
        return $this->Taille;
    }

    public function setTaille(float $Taille): self
    {
        $this->Taille = $Taille;

        return $this;
    }

    public function getImc(): ?float
    {
        return $this->Imc;
    }

    public function setImc(float $Imc): self
    {
        $this->Imc = $Imc;

        return $this;
    }

    public function getSymptome(): ?string
    {
        return $this->Symptome;
    }

    public function setSymptome(?string $Symptome): self
    {
        $this->Symptome = $Symptome;

        return $this;
    }

    public function getApetit(): ?string
    {
        return $this->Apetit;
    }

    public function setApetit(string $Apetit): self
    {
        $this->Apetit = $Apetit;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->Description;
    }

    public function setDescription(?string $Description): self
    {
        $this->Description = $Description;

        return $this;
    }
}
