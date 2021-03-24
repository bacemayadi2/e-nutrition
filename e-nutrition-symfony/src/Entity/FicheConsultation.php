<?php

namespace App\Entity;

use App\Repository\FicheConsultationRepository;
use App\Repository\PatientRepository;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use phpDocumentor\Reflection\Types\Array_;

/**
 * @ORM\Entity(repositoryClass=FicheConsultationRepository::class)
 */
class FicheConsultation
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
    private $CreationDate;

    /**
     * @ORM\Column(type="float")
     */
    private $Poids;

    /**
     * @ORM\Column(type="float")
     */
    private $Taille;



    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $Symptome;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $Apetit;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $Description;

    /**
     * @ORM\OneToMany(targetEntity=Medicament::class, mappedBy="fiche",cascade={"all"},orphanRemoval=true)
     */
    private $medicaments;

    /**
     * @ORM\OneToMany(targetEntity=TagFicheConsultation::class, mappedBy="ficheConsultation",cascade={"all"},orphanRemoval=true)
     */
    private $tagFicheConsultation;

    /**
     * @ORM\ManyToOne(targetEntity=Patient::class, inversedBy="ficheConsultations")
     */
    private $patient;

    /**
     * @ORM\ManyToOne(targetEntity=Nutritionniste::class, inversedBy="ficheConsultations")
     */
    private $nutritionniste;

    public function __construct()
    {
        $this->medicaments = new ArrayCollection();
        $this->tagFicheConsultation = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getCreationDate(): ?\DateTimeInterface
    {
        return $this->CreationDate;
    }

    public function setCreationDate(\DateTimeInterface $CreationDate): self
    {
        $this->CreationDate = $CreationDate;

        return $this;
    }

    public function getPoids(): ?float
    {
        return $this->Poids;
    }

    public function setPoids(float $Poids): self
    {
        $this->Poids = $Poids;

        return $this;
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


    public function getSymptome(): ?string
    {
        return $this->Symptome;
    }

    public function setSymptome(?string $Symptome): self
    {
        $this->Symptome = $Symptome;

        return $this;
    }

    public function getApetit(): ?string
    {
        return $this->Apetit;
    }

    public function setApetit(string $Apetit): self
    {
        $this->Apetit = $Apetit;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->Description;
    }

    public function setDescription(?string $Description): self
    {
        $this->Description = $Description;

        return $this;
    }

    /**
     * @return Collection|Medicament[]
     */
    public function getMedicaments(): Collection
    {
        return $this->medicaments;
    }

    public function addMedicament(Medicament $medicament): self
    {
        if (!$this->medicaments->contains($medicament)) {
            $this->medicaments[] = $medicament;
            $medicament->setFiche($this);
        }

        return $this;
    }

    public function removeMedicament(Medicament $medicament): self
    {
        if ($this->medicaments->removeElement($medicament)) {
            // set the owning side to null (unless already changed)
            if ($medicament->getFiche() === $this) {
                $medicament->setFiche(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|TagFicheConsultation[]
     */
    public function getTagFicheConsultation(): Collection
    {
        return $this->tagFicheConsultation;
    }

    public function addTagFicheConsultation(TagFicheConsultation $tagFicheConsultation): self
    {
        if (!$this->tagFicheConsultation->contains($tagFicheConsultation)) {
            $this->tagFicheConsultation[] = $tagFicheConsultation;
            $tagFicheConsultation->setFicheConsultation($this);
        }

        return $this;
    }

    public function removeTagFicheConsultation(TagFicheConsultation $tagFicheConsultation): self
    {
        if ($this->tagFicheConsultation->removeElement($tagFicheConsultation)) {
            // set the owning side to null (unless already changed)
            if ($tagFicheConsultation->getFicheConsultation() === $this) {
                $tagFicheConsultation->setFicheConsultation(null);
            }
        }

        return $this;
    }

    public function getPatient(): ?Patient
    {
        return $this->patient;
    }

    public function setPatient(?Patient $patient): self
    {
        $this->patient = $patient;

        return $this;
    }

    public function getNutritionniste(): ?Nutritionniste
    {
        return $this->nutritionniste;
    }

    public function setNutritionniste(?Nutritionniste $nutritionniste): self
    {
        $this->nutritionniste = $nutritionniste;

        return $this;
    }


    public function calculerImc() :float
    {
        return         number_format(($this->getTaille())/($this->getPoids()*$this->getPoids()),2);
    }

    public function  getnbrOFTimeUsed():float
    {
        $j=0;
        foreach ($this->getPatient() ->getFicheConsultations() as $ficheConsultation )
        {
            $j++;
        }
        return$j;
    }



}
