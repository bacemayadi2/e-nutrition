<?php

namespace App\Entity;

use App\Repository\AlimentRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use PhpParser\Node\Scalar\String_;

/**
 * @ORM\Entity(repositoryClass=AlimentRepository::class)
 */
class Aliment extends Nourriture
{


    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */

    private $codeABarre;

    /**
     * @ORM\ManyToMany(targetEntity=CategorieAliment::class, inversedBy="aliments",cascade={"persist"})
     */
    private $categorieAliment;

    /**
     * @ORM\OneToMany(targetEntity=Composition::class, mappedBy="aliments")
     */
    private $compositions;



    public function __construct()
    {
        $this->categorieAliment = new ArrayCollection();
        $this->compositions = new ArrayCollection();
    }


    public function getCodeABarre(): ?string
    {
        return $this->codeABarre;
    }

    public function setCodeABarre(?string $codeABarre): self
    {
        $this->codeABarre = $codeABarre;

        return $this;
    }

    /**
     * @return Collection|CategorieAliment[]
     */
    public function getCategorieAliment(): Collection
    {
        return $this->categorieAliment;
    }

    public function  categorieToString(): string
    {
        $s="";
        $first=true;
        foreach ($this->categorieAliment as $c)
        {
         if  ($first == false)
             $s= $s .  " , " ;
         $s= $s . $c ;
         $first=false;
        }

        return $s;
    }

    public function addCategorieAliment(CategorieAliment $categorieAliment): self
    {
        if (!$this->categorieAliment->contains($categorieAliment)) {
            $this->categorieAliment[] = $categorieAliment;
        }

        return $this;
    }

    public function removeCategorieAliment(CategorieAliment $categorieAliment): self
    {
        $this->categorieAliment->removeElement($categorieAliment);

        return $this;
    }


/**
 * @return Collection|Composition[]
 */
public function getCompositions(): Collection
{
    return $this->compositions;
}

public function addComposition(Composition $composition): self
{
    if (!$this->compositions->contains($composition)) {
        $this->compositions[] = $composition;
        $composition->setAliment($this);
    }

    return $this;
}

public function removeComposition(Composition $composition): self
{
    if ($this->compositions->removeElement($composition)) {
        // set the owning side to null (unless already changed)
        if ($composition->getAliment() === $this) {
            $composition->setAliment(null);
        }
    }

    return $this;
}

    public function __toString() {
        // to show the name of the Category in the select
        return $this->getNom();

    }
}
