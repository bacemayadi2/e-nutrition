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
     * @ORM\ManyToOne(targetEntity=Aliment::class, inversedBy="proportions")
     * @ORM\JoinColumn(nullable=false)
     */
    private $aliment;

    /**
     * @ORM\Column(type="float")
     */
    private $lipides;

    /**
     * @ORM\Column(type="float")
     */
    private $glucides;

    /**
     * @ORM\Column(type="float")
     */
    private $proteines;

    /**
     * @ORM\Column(type="float")
     */
    private $calorie;


    public function calculatenutritionvalue(): self
    {
        $aliment= $this->getAliment();
         $this->setProteines(($aliment->getProteines()/$aliment->getPoid())*$this->getPoid());
         $this->setGlucides(($aliment->getGlucides()/$aliment->getPoid())*$this->getPoid());
         $this->setLipides(($aliment->getLipides()/$aliment->getPoid())*$this->getPoid());
         $this->setCalorie(($aliment->getCalories()/$aliment->getPoid())*$this->getPoid());
         return $this;
    }





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

    public function getLipides(): ?float
    {
        return $this->lipides;
    }

    public function setLipides(float $lipides): self
    {
        $this->lipides = $lipides;

        return $this;
    }

    public function getGlucides(): ?float
    {
        return $this->glucides;
    }

    public function setGlucides(float $glucides): self
    {
        $this->glucides = $glucides;

        return $this;
    }

    public function getProteines(): ?float
    {
        return $this->proteines;
    }

    public function setProteines(float $proteines): self
    {
        $this->proteines = $proteines;

        return $this;
    }

    public function getCalorie(): ?float
    {
        return $this->calorie;
    }

    public function setCalorie(float $calorie): self
    {
        $this->calorie = $calorie;

        return $this;
    }
}
