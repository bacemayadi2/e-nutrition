<?php

namespace App\Entity;

use App\Repository\CompositionRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=CompositionRepository::class)
 */
class Composition
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
     * @ORM\ManyToOne(targetEntity=Aliment::class, inversedBy="compositions")
     */
    private $aliments;

    /**
     * @ORM\ManyToOne(targetEntity=Plat::class, inversedBy="compostions")
     */
    private $plat;


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

    public function getAliments(): ?Aliment
    {
        return $this->aliments;
    }

    public function setAliments(?Aliment $aliments): self
    {
        $this->aliments = $aliments;

        return $this;
    }

    public function getPlat(): ?Plat
    {
        return $this->plat;
    }

    public function setPlat(?Plat $plat): self
    {
        $this->plat = $plat;

        return $this;
    }

}
