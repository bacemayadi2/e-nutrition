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
     * @return mixed
     */
    public function getId()
    {
        return $this->id;
    }


    /**
     * @ORM\OneToMany(targetEntity=ChallengeTag::class, mappedBy="tagsChallenge" ,cascade={"all"},orphanRemoval=true)
     */
    private $tags;

    public function __construct()
    {
        $this->tags = new ArrayCollection();
    }

    /**
     * @return Collection|ChallengeTag[]
     */
    public function getTags(): Collection
    {
        return $this->tags;
    }

    public function addTag(ChallengeTag $tag): self
    {
        if (!$this->tags->contains($tag)) {
            $this->tags[] = $tag;
            $tag->setTagsChallenge($this);
        }

        return $this;
    }

    public function removeTag(ChallengeTag $tag): self
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
