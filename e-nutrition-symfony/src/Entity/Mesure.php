<?php

namespace App\Entity;

use App\Repository\MesureRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=MesureRepository::class)
 */
class Mesure
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
    private $dateMesure;

    /**
     * @ORM\Column(type="float", nullable=true)
     */
    private $poid;

    /**
     * @ORM\Column(type="float", nullable=true)
     */
    private $taille;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getDateMesure(): ?\DateTimeInterface
    {
        return $this->dateMesure;
    }

    public function setDateMesure(\DateTimeInterface $dateMesure): self
    {
        $this->dateMesure = $dateMesure;

        return $this;
    }

    public function getPoid(): ?float
    {
        return $this->poid;
    }

    public function setPoid(?float $poid): self
    {
        $this->poid = $poid;

        return $this;
    }

    public function getTaille(): ?float
    {
        return $this->taille;
    }

    public function setTaille(?float $taille): self
    {
        $this->taille = $taille;

        return $this;
    }
}
