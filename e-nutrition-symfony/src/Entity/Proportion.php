<?php

namespace App\Entity;

use App\Repository\ProportionRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=ProportionRepository::class)
 */
class Proportion
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="float")
     */
    private $poid;

    /**
     * @ORM\Column(type="datetime")
     */
    private $date;



    /**
     * @ORM\ManyToOne(targetEntity=Patient::class, inversedBy="proportions")
     */
    private $patient;

    /**
     * @ORM\ManyToOne(targetEntity=aliment::class, inversedBy="proportions")
     * @ORM\JoinColumn(nullable=false)
     */
    private $aliment;



    public function getId(): ?int
    {
        return $this->id;
    }

    public function getPoid(): ?float
    {
        return $this->poid;
    }

    public function setPoid(float $poid): self
    {
        $this->poid = $poid;

        return $this;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }







    public function getPatient(): ?Patient
    {
        return $this->patient;
    }

    public function setPatient(?Patient $patient): self
    {
        $this->patient = $patient;

        return $this;
    }

    public function getAliment(): ?aliment
    {
        return $this->aliment;
    }

    public function setAliment(?aliment $aliment): self
    {
        $this->aliment = $aliment;

        return $this;
    }
}
