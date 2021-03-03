<?php

namespace App\Entity;

use App\Repository\NourritureRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=NourritureRepository::class)
 * @ORM\InheritanceType("JOINED")
 */
class Nourriture
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    Protected $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    Protected $nom;

    /**
     * @ORM\Column(type="float")
     */
    Protected $lipides;

    /**
     * @ORM\Column(type="float")
     */
    Protected $glucides;

    /**
     * @ORM\Column(type="float")
     */
    Protected $proteines;

    /**
     * @ORM\Column(type="float")
     */
    Protected $poid;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

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

    public function getPoid(): ?float
    {
        return $this->poid;
    }

    public function setPoid(float $poid): self
    {
        $this->poid = $poid;

        return $this;
    }
}
