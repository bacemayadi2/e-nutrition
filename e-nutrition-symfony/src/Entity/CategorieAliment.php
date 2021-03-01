<?php

namespace App\Entity;

use App\Repository\CategorieAlimentRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=CategorieAlimentRepository::class)
 */
class CategorieAliment
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $nomCategorie;

    /**
     * @ORM\OneToMany(targetEntity=Aliment::class, mappedBy="categorieAliment")
     */
    private $aliments;

    public function __construct()
    {
        $this->aliments = new ArrayCollection();
    }


    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNomCategorie(): ?string
    {
        return $this->nomCategorie;
    }

    public function setNomCategorie(string $nomCategorie): self
    {
        $this->nomCategorie = $nomCategorie;

        return $this;
    }

    /**
     * @return Collection|Aliment[]
     */
    public function getAliments(): Collection
    {
        return $this->aliments;
    }

    public function addAliment(Aliment $aliment): self
    {
        if (!$this->aliments->contains($aliment)) {
            $this->aliments[] = $aliment;
            $aliment->setCategorieAliment($this);
        }

        return $this;
    }

    public function removeAliment(Aliment $aliment): self
    {
        if ($this->aliments->removeElement($aliment)) {
            // set the owning side to null (unless already changed)
            if ($aliment->getCategorieAliment() === $this) {
                $aliment->setCategorieAliment(null);
            }
        }

        return $this;
    }


}
