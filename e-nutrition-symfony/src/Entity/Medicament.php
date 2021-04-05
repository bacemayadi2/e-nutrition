<?php

namespace App\Entity;

use App\Repository\MedicamentRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups as Groups;

/**
 * @ORM\Entity(repositoryClass=MedicamentRepository::class)
 */
class Medicament
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     * @Groups ("medicaments")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Groups ("medicaments")
     */
    private $nom;

    /**
     * @ORM\Column(type="string", length=255)
     * @Groups ("medicaments")
     */
    private $quantite;

    /**
     * @ORM\Column(type="string", length=255)
     * @Groups ("medicaments")
     */
    private $duree;

    /**
     * @ORM\ManyToOne(targetEntity=FicheConsultation::class, inversedBy="medicaments")
     * @Groups ("medicaments")
     */
    private $fiche;

    /**
     * @Groups ("medicaments")
     */
    private  $idfiche;

    /**
     * @return mixed
     */
    public function getIdfiche()
    {
        $this->setIdfiche();
        return $this->idfiche;
    }

    /**
     * @param mixed $idfiche
     */
    public function setIdfiche(): void
    {
        $this->idfiche = $this->fiche->getId();
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

    public function getQuantite(): ?string
    {
        return $this->quantite;
    }

    public function setQuantite(string $quantite): self
    {
        $this->quantite = $quantite;

        return $this;
    }

    public function getDuree(): ?string
    {
        return $this->duree;
    }

    public function setDuree(string $duree): self
    {
        $this->duree = $duree;

        return $this;
    }

    public function getFiche(): ?FicheConsultation
    {
        return $this->fiche;
    }

    public function setFiche(?FicheConsultation $fiche): self
    {
        $this->fiche = $fiche;

        return $this;
    }
}
