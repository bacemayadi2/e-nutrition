<?php

namespace App\Entity;

use App\Repository\TagsChallengeRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=TagsChallengeRepository::class)
 */
class TagsChallenge
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\ManyToOne(targetEntity=TagsChallenge::class, inversedBy="tags",cascade={"all"})
     */
    private $tagsChallenge;

    /**
     * @ORM\OneToMany(targetEntity=TagsChallenge::class, mappedBy="tagsChallenge")
     */
    private $tags;

    public function __construct()
    {
        $this->tags = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getTagsChallenge(): ?self
    {
        return $this->tagsChallenge;
    }

    public function setTagsChallenge(?self $tagsChallenge): self
    {
        $this->tagsChallenge = $tagsChallenge;

        return $this;
    }

    /**
     * @return Collection|self[]
     */
    public function getTags(): Collection
    {
        return $this->tags;
    }

    public function addTag(self $tag): self
    {
        if (!$this->tags->contains($tag)) {
            $this->tags[] = $tag;
            $tag->setTagsChallenge($this);
        }

        return $this;
    }

    public function removeTag(self $tag): self
    {
        if ($this->tags->removeElement($tag)) {
            // set the owning side to null (unless already changed)
            if ($tag->getTagsChallenge() === $this) {
                $tag->setTagsChallenge(null);
            }
        }

        return $this;
    }
}
