<?php

namespace App\Entity;

use App\Repository\CategorieAlimentRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use ApiPlatform\Core\Annotation\ApiResource;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * @ORM\Entity(repositoryClass=CategorieAlimentRepository::class)
 * @ApiResource()
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
     * @Groups ("aliment:read")
     */
    private $nomCategorie;

    /**
     * @ORM\ManyToMany(targetEntity=Aliment::class, mappedBy="categorieAliment")
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
            $aliment->addCategorieAliment($this);
        }

        return $this;
    }

    public function removeAliment(Aliment $aliment): self
    {
        if ($this->aliments->removeElement($aliment)) {
            $aliment->removeCategorieAliment($this);
        }

        return $this;
    }

    public function __toString() {
        // to show the name of the Category in the select
        return $this->nomCategorie ;

    }

    public function getNumberOfAliment(){
        $i=0;
        foreach ($this->aliments as $aliment)
        {
            $i++;
        }
        return $i;


    }
}
