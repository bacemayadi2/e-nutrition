<?php

namespace App\Entity;

use App\Repository\AlimentRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use PhpParser\Node\Scalar\String_;
use Symfony\Component\Serializer\Annotation\Groups as Groups;


/**
 * @ORM\Entity(repositoryClass=AlimentRepository::class)
 */
class Aliment extends Nourriture
{


    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     *
     */

    private $codeABarre;

    /**
     * @ORM\ManyToMany(targetEntity=CategorieAliment::class, inversedBy="aliments",cascade={"persist"})
     */
    private $categorieAliment;

    /**
     * @ORM\OneToMany(targetEntity=Composition::class, mappedBy="aliment")
     *
     */
    private $compositions;

    /**
     * @Groups ("aliments")
     */
    private $calories;

    /**
     * @Groups ("aliments")
     */
    private $categories;

    /**
     * @ORM\OneToMany(targetEntity=Proportion::class, mappedBy="aliment", orphanRemoval=true)
     */
    private $proportions;


    private $numberofplat;

    /**
     * @return mixed
     */
    public function getNumberofplat()
    {
        $this->setNumberofplat();
        return $this->numberofplat;
    }

    /**
     * @param mixed $numberofplat
     */
    public function setNumberofplat(): void
    {
        $this->numberofplat = $this->setNumberofplat();
    }




    public function getCalories(): ?float
    {
        $this->setCalories();
        return $this->calories;
    }

    /**
     * @param mixed $calories
     */
    public function setCalories(): void
    {
        $this->calories = $this->calculerCalorie();
    }

    /**
     * @return mixed
     */
    public function getCategories() :string
    {
        $this->setCategories();
        return $this->categories;
    }

    /**
     * @param mixed $categories
     */
    public function setCategories(): void
    {
        $this->categories = $this->categorieToString();
    }







    public function __construct()
    {
        $this->categorieAliment = new ArrayCollection();
        $this->compositions = new ArrayCollection();
        $this->proportions = new ArrayCollection();
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

    /**
     * @return string
     */
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

public function  getnbrOFTimeUsed():float
{
    $j=0;
    foreach ($this->getCompositions()  as $compo )
    {
        $j++;
    }
    return$j;
}


    public function __toString() {
        // to show the name of the Category in the select
        return $this->getNom();

    }

    /**
     * @return Collection|Proportion[]
     */
    public function getProportions(): Collection
    {
        return $this->proportions;
    }

    public function addProportion(Proportion $proportion): self
    {
        if (!$this->proportions->contains($proportion)) {
            $this->proportions[] = $proportion;
            $proportion->setAliment($this);
        }

        return $this;
    }

    public function removeProportion(Proportion $proportion): self
    {
        if ($this->proportions->removeElement($proportion)) {
            // set the owning side to null (unless already changed)
            if ($proportion->getAliment() === $this) {
                $proportion->setAliment(null);
            }
        }

        return $this;
    }





}
