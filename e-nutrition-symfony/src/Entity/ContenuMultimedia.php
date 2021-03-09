<?php

namespace App\Entity;

use App\Repository\ContenuMultimediaRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Vich\UploaderBundle\Entity\File;
use Vich\UploaderBundle\Mapping\Annotation as Vich;

/**
 * @ORM\Entity(repositoryClass=ContenuMultimediaRepository::class)
 * @ORM\InheritanceType("JOINED")
 * @Vich\Uploadable()
 */
class ContenuMultimedia
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;


    /**
     * @Vich\UploadableField(mapping="multimedia", fileNameProperty="nomFile")
     * @var File|null
     */
    private $fileMultimedia;



    /**
     * @ORM\Column(type="datetime")
     *  @var \DateTimeInterface|null
     */
    private $updatedAt;

    /**
     * @ORM\Column(type="string", length=1000, nullable=true)
     */
    private $description;

    /**
     * @ORM\Column(type="string", length=1000)
     *  @var string|null
     */
    private $nomFile;

    /**
     * @ORM\OneToMany(targetEntity=Tag::class, mappedBy="contenuMultimedia", orphanRemoval=true)
     */
    private $tag;


    public function __construct()
    {
        $this->tag = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }



    public function getUpdatedAt(): ?\DateTimeInterface
    {
        return $this->updatedAt;
    }

    public function setUpdatedAt(\DateTimeInterface $updatedAt): self
    {
        $this->updatedAt = $updatedAt;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(?string $description): self
    {
        $this->description = $description;

        return $this;
    }


    public function setNomFile($nomFile): void
    {
        $this->nomFile = $nomFile;
    }

    public function getNomFile(): ?string
    {
        return $this->nomFile;
    }

    public function setFileMultimedia(File $fileMultimedia = null): self
    {
        $this->fileMultimedia = $fileMultimedia;

        // VERY IMPORTANT:
        // It is required that at least one field changes if you are using Doctrine,
        // otherwise the event listeners won't be called and the file is lost
        if (null !== $fileMultimedia) {
            // if 'updatedAt' is not defined in your entity, use another property
            $this->updatedAt = new \DateTimeImmutable();
        }
    }

    /**
     * @return Collection|Tag[]
     */
    public function getTag(): Collection
    {
        return $this->tag;
    }

    public function addTag(Tag $tag): self
    {
        if (!$this->tag->contains($tag)) {
            $this->tag[] = $tag;
            $tag->setContenuMultimedia($this);
        }

        return $this;
    }

    public function removeTag(Tag $tag): self
    {
        if ($this->tag->removeElement($tag)) {
            // set the owning side to null (unless already changed)
            if ($tag->getContenuMultimedia() === $this) {
                $tag->setContenuMultimedia(null);
            }
        }

        return $this;
    }
    public function getFileMultimedia() : ?File
    {
        return $this->fileMultimedia;
    }
}
