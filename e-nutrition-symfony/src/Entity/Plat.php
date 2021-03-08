<?php

namespace App\Entity;

use App\Repository\PlatRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=PlatRepository::class)
 */
class Plat extends Nourriture
{


    /**
     * @ORM\Column(type="string", length=1000)
     */
    private $description;

    /**
     * @ORM\Column(type="integer")
     */
    private $nbrportion;

    /**
     * @ORM\OneToMany(targetEntity=EtapeDePreparation::class, mappedBy="plat",cascade={"persist"})
     */
    private $etapeDePreparation;

    /**
     * @ORM\OneToMany(targetEntity=Composition::class, mappedBy="plat", cascade={"persist"})
     */
    private $compostions;

    public function __construct()
    {
        $this->etapeDePreparation = new ArrayCollection();
        $this->compostions = new ArrayCollection();
    }



    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }




    public function getNbrportion(): ?int
    {
        return $this->nbrportion;
    }

    public function setNbrportion(int $nbrportion): self
    {
        $this->nbrportion = $nbrportion;

        return $this;
    }

    /**
     * @return Collection|EtapeDePreparation[]
     */
    public function getEtapeDePreparation(): Collection
    {
        return $this->etapeDePreparation;
    }

    public function addEtapeDePreparation(EtapeDePreparation $etapeDePreparation): self
    {
        if (!$this->etapeDePreparation->contains($etapeDePreparation)) {
            $etapeDePreparation->setOrdre(sizeof($this->etapeDePreparation));
            $this->etapeDePreparation[] = $etapeDePreparation;
            $etapeDePreparation->setPlat($this);
        }

        return $this;
    }

    public function removeEtapeDePreparation(EtapeDePreparation $etapeDePreparation): self
    {
        if ($this->etapeDePreparation->removeElement($etapeDePreparation)) {
            // set the owning side to null (unless already changed)
            if ($etapeDePreparation->getPlat() === $this) {
                $etapeDePreparation->setPlat(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|Composition[]
     */
    public function getCompostions(): Collection
    {
        return $this->compostions;
    }

    public function addCompostion(Composition $compostion): self
    {
        if (!$this->compostions->contains($compostion)) {
            $this->compostions[] = $compostion;
            $compostion->setPlat($this);
        }

        return $this;
    }

    public function removeCompostion(Composition $compostion): self
    {
        if ($this->compostions->removeElement($compostion)) {
            // set the owning side to null (unless already changed)
            if ($compostion->getPlat() === $this) {
                $compostion->setPlat(null);
            }
        }

        return $this;
    }
    public function calculeLipides():self
    {
        $this->lipides=4;
        return $this;
    }
    public function calculeGlucides():self
    {
        $this->glucides=4;
        return $this;
    }
    public function calculeProteines():self
    {
        $this->proteines=4;
        return $this;
    }
    public function calculePoids():self
    {
        $this->poid=4;
        return $this;
    }
    public function calculeNutritiments():self
    {
        $this->calculeLipides();
        $this->calculeGlucides();
        $this->calculeProteines();
        $this->calculePoids();
        return $this;
    }

    public function __toString()
    {
        return $this->getNom();
    }





}
