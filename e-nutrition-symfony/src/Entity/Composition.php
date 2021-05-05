<?php

namespace App\Entity;

use App\Repository\CompositionRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups as Groups;

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
     * @Groups ("plat:read")
     */
    private $poid;

    /**
     * @ORM\ManyToOne(targetEntity=Aliment::class, inversedBy="compositions" , cascade={"persist"})
     * @Groups ("plat:read")
     */
    private $aliment;

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

    public function getAliment(): ?Aliment
    {
        return $this->aliment;
    }

    public function setAliment(?Aliment $aliment): self
    {
        $this->aliment = $aliment;

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
    public function calculerCalorieParpoid() :float
    {
        return (($this->getAliment()->calculerCalorie()/$this->getAliment()->getPoid())*$this->poid);
    }



}
