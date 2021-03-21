<?php

namespace App\Entity;

use App\Repository\ImageRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=ImageRepository::class)
 */
class Image extends ContenuMultimedia
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;


    /**
     * @ORM\ManyToOne(targetEntity=SuccessStory::class, inversedBy="listeImages")
     */
    private $successStories;

    public function __construct()
    {
        parent::__construct();
        $this->successStories = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    /**
     * @return Collection|SuccessStory[]
     */
    public function getSuccessStories(): Collection
    {
        return $this->successStories;
    }

    public function addSuccessStory(SuccessStory $successStory): self
    {
        if (!$this->successStories->contains($successStory)) {
            $this->successStories[] = $successStory;
            $successStory->setListeImages($this);
        }

        return $this;
    }

    public function removeSuccessStory(SuccessStory $successStory): self
    {
        if ($this->successStories->removeElement($successStory)) {
            // set the owning side to null (unless already changed)
            if ($successStory->getListeImages() === $this) {
                $successStory->setListeImages(null);
            }
        }

        return $this;
    }
}
