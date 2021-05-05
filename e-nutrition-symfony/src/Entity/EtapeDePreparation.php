<?php

namespace App\Entity;

use App\Repository\EtapeDePreparationRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups as Groups;


/**
 * @ORM\Entity(repositoryClass=EtapeDePreparationRepository::class)
 */
class EtapeDePreparation
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="integer")
     * @Groups ("plat:read")
     */
    private $ordre;

    /**
     * @ORM\Column(type="integer")
     * @Groups ("plat:read")
     */
    private $duree;

    /**
     * @ORM\Column(type="string", length=1000)
     * @Groups ("plat:read")
     */
    private $description;

    /**
     * @ORM\ManyToOne(targetEntity=Plat::class, inversedBy="etapeDePreparation")
     */
    private $plat;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getOrdre(): ?int
    {
        return $this->ordre;
    }

    public function setOrdre(int $ordre): self
    {
        $this->ordre = $ordre;

        return $this;
    }

    public function getDuree(): ?int
    {
        return $this->duree;
    }

    public function setDuree(int $duree): self
    {
        $this->duree = $duree;

        return $this;
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
