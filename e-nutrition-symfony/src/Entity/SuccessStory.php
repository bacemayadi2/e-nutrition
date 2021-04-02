<?php

namespace App\Entity;

use App\Repository\SuccessStoryRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;


/**
 * @ORM\Entity(repositoryClass=SuccessStoryRepository::class)
 */
class SuccessStory
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $titre;

    /**
     * @ORM\Column(type="string", length=10000)
     */
    private $text;

    /**
     * @ORM\OneToMany(targetEntity=TagSuccessStory::class, mappedBy="successStory", cascade={"all"},orphanRemoval=true)
     */
    private $tagSucess;

    /**
     * @ORM\Column(type="datetime", nullable=true)
     */
    private $dateCreation;


    /**
     * @ORM\Column(type="integer", nullable=true)
     */
    private $likeStory;

    /**
     * @ORM\OneToMany(targetEntity=Comments::class, mappedBy="success", orphanRemoval=true ,cascade={"all"})
     */
    private $comments;

    public function __construct()
    {
        $this->tagSucess = new ArrayCollection();
        $this->comments = new ArrayCollection();
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

    public function getText(): ?string
    {
        return $this->text;
    }

    public function setText(string $text): self
    {
        $this->text = $text;

        return $this;
    }

    /**
     * @return Collection|TagSuccessStory[]
     */
    public function getTagSucess(): Collection
    {
        return $this->tagSucess;
    }

    public function addTagSucess(TagSuccessStory $tagSucess): self
    {
        if (!$this->tagSucess->contains($tagSucess)) {
            $this->tagSucess[] = $tagSucess;
            $tagSucess->setSuccessStory($this);
        }

        return $this;
    }

    public function removeTagSucess(TagSuccessStory $tagSucess): self
    {
        if ($this->tagSucess->removeElement($tagSucess)) {
            // set the owning side to null (unless already changed)
            if ($tagSucess->getSuccessStory() === $this) {
                $tagSucess->setSuccessStory(null);
            }
        }

        return $this;
    }

    public function getDateCreation(): ?\DateTimeInterface
    {
        return $this->dateCreation;
    }

    public function setDateCreation(?\DateTimeInterface $dateCreation): self
    {
        $this->dateCreation = $dateCreation;

        return $this;
    }





    public function getLikeStory(): ?int
    {
        return $this->likeStory;
    }

    public function setLikeStory(?int $likeStory): self
    {
        $this->likeStory = $likeStory;

        return $this;
    }

    /**
     * @return Collection|Comments[]
     */
    public function getComments(): Collection
    {
        return $this->comments;
    }

    public function addComment(Comments $comment): self
    {
        if (!$this->comments->contains($comment)) {
            $this->comments[] = $comment;
            $comment->setSuccess($this);
        }

        return $this;
    }

    public function removeComment(Comments $comment): self
    {
        if ($this->comments->removeElement($comment)) {
            // set the owning side to null (unless already changed)
            if ($comment->getSuccess() === $this) {
                $comment->setSuccess(null);
            }
        }

        return $this;
    }
}
