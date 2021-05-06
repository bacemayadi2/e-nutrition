<?php

namespace App\Entity;

use App\Repository\ChallengeRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups as Groups;

/**
 * @ORM\Entity(repositoryClass=ChallengeRepository::class)
 */
class Challenge
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Saisir le titre !!!")
     * @Groups ("challenges:read")
     */
    private $titre;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Saisir la description !!!")
     * @Groups ("challenges:read")
     */
    private $description;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Saisir la categorie !!!")
     * @Groups ("challenges:read")
     */
    private $categorie;

    /**
     * @ORM\Column(type="date")
     * @Groups ("challenges:read")
     */
    private $dateDebut;

    /**
     * @ORM\Column(type="date")
     * @Groups ("challenges:read")
     */
    private $dateFin;

    /**
     * @ORM\ManyToMany(targetEntity=Patient::class, mappedBy="challenges", cascade={"all"})
     */
    private $participants;

    /**
     * @ORM\OneToMany(targetEntity=ChallengeTag::class, mappedBy="challenge",cascade={"all"},orphanRemoval=true)
     */
    private $tagChallenge;

    public function __construct()
    {
        $this->participants = new ArrayCollection();
        $this->tagChallenge = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getTitre(): ?string
    {
        return $this->titre;
    }

    public function setTitre(string $titre): self
    {
        $this->titre = $titre;

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

    public function getCategorie(): ?string
    {
        return $this->categorie;
    }

    public function setCategorie(string $categorie): self
    {
        $this->categorie = $categorie;

        return $this;
    }

    public function getDateDebut(): ?\DateTimeInterface
    {
        return $this->dateDebut;
    }

    public function setDateDebut(\DateTimeInterface $dateDebut): self
    {
        $this->dateDebut = $dateDebut;

        return $this;
    }

    public function getDateFin(): ?\DateTimeInterface
    {
        return $this->dateFin;
    }

    public function setDateFin(\DateTimeInterface $dateFin): self
    {
        $this->dateFin = $dateFin;

        return $this;
    }

    /**
     * @return Collection|Patient[]
     */
    public function getParticipants(): Collection
    {
        return $this->participants;
    }

    public function addParticipant(Patient $participant): self
    {
        if (!$this->participants->contains($participant)) {
            $this->participants[] = $participant;
            $participant->addChallenge($this);
        }

        return $this;
    }

    public function removeParticipant(Patient $participant): self
    {
        if ($this->participants->removeElement($participant)) {
            $participant->removeChallenge($this);
        }

        return $this;
    }

    /**
     * @return Collection|ChallengeTag[]
     */
    public function getTagChallenge(): Collection
    {
        return $this->tagChallenge;
    }

    public function addTagChallenge(ChallengeTag $tagChallenge): self
    {
        if (!$this->tagChallenge->contains($tagChallenge)) {
            $this->tagChallenge[] = $tagChallenge;
            $tagChallenge->setChallenge($this);
        }

        return $this;
    }

    public function removeTagChallenge(ChallengeTag $tagChallenge): self
    {
        if ($this->tagChallenge->removeElement($tagChallenge)) {
            // set the owning side to null (unless already changed)
            if ($tagChallenge->getChallenge() === $this) {
                $tagChallenge->setChallenge(null);
            }
        }

        return $this;
    }
}
