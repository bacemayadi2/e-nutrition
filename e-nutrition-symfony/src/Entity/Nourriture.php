<?php

namespace App\Entity;

use App\Repository\NourritureRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups as Groups;

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
     * @Groups ("aliments")
     */
    Protected $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Groups ("aliments")
     */
    Protected $nom;

    /**
     * @ORM\Column(type="float")
     * @Groups ("aliments")
     */
    Protected $lipides;

    /**
     * @ORM\Column(type="float")
     * @Groups ("aliments")
     */
    Protected $glucides;

    /**
     * @ORM\Column(type="float")
     * @Groups ("aliments")
     */
    Protected $proteines;

    /**
     * @ORM\Column(type="float")
     * @Groups ("aliments")
     */
    Protected $poid;

    /**
     * @ORM\OneToMany(targetEntity=TagNourriture::class, mappedBy="nourriture", cascade={"all"},orphanRemoval=true)
     */
    protected $tagNourriture;

    /**
     * @ORM\ManyToOne(targetEntity=Utilisateur::class, inversedBy="nourritures")
     */
    private $nutritionniste;

    public function __construct()
    {
        $this->tagNourriture = new ArrayCollection();
    }

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
    public function calculerCalorie() :float
    {
       // $number=number_format( $this->getGlucides()*4 +$this->getLipides() *9 + $this->getProteines()*9,2);
        return $this->getGlucides()*4 +$this->getLipides() *9 + $this->getProteines()*9;
    }

    /**
     * @return Collection|TagNourriture[]
     */
    public function getTagNourriture(): ? Collection
    {
        return $this->tagNourriture;
    }

    public function addTagNourriture(TagNourriture $tagNourriture): self
    {
        if (!$this->tagNourriture->contains($tagNourriture)) {
            $this->tagNourriture[] = $tagNourriture;
            $tagNourriture->setNourriture($this);
        }


        return $this;
    }

    public function removeTagNourriture(TagNourriture $tagNourriture): self
    {
        if ($this->tagNourriture->removeElement($tagNourriture)) {
            // set the owning side to null (unless already changed)
            if ($tagNourriture->getNourriture() === $this) {
                $tagNourriture->setNourriture(null);
            }
        }

        return $this;
    }

    public function getNutritionniste(): ?Utilisateur
    {
        return $this->nutritionniste;
    }

    public function setNutritionniste(?Utilisateur $nutritionniste): self
    {
        $this->nutritionniste = $nutritionniste;

        return $this;
    }

}
