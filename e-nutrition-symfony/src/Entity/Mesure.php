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
     * @ORM\Column(type="float")
     */
    private $Taille;

    /**
     * @ORM\Column(type="float")
     */
    private $poids;

    /**
     * @ORM\Column(type="datetime")
     */
    private $dateMesure;

    public function getId(): ?int
    {
        return $this->id;
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

    public function getPoids(): ?float
    {
        return $this->poids;
    }

    public function setPoids(float $poids): self
    {
        $this->poids = $poids;

        return $this;
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

    public function calculerIMC(){
        return round(($this->poids / ($this->Taille ** 2)), 2);
    }

    public function poidsIdeal(){
        // 50 + (0.91 × [height in centimeters − 152.4])
        return 50 + (0.91 * ($this->Taille * 100 - 152.4));
    }
}
