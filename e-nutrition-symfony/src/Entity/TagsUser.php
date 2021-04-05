<?php

namespace App\Entity;

use App\Repository\TagsUserRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=TagsUserRepository::class)
 */
class TagsUser
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\OneToMany(targetEntity=TagUtilisateur::class, mappedBy="tagsUser", cascade={"all"},orphanRemoval=true)
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

    /**
     * @return Collection|TagUtilisateur[]
     */
    public function getTags(): Collection
    {
        return $this->tags;
    }

    public function addTag(TagUtilisateur $tag): self
    {
        if (!$this->tags->contains($tag)) {
            $this->tags[] = $tag;
            $tag->setTagsUser($this);
        }

        return $this;
    }

    public function removeTag(TagUtilisateur $tag): self
    {
        if ($this->tags->removeElement($tag)) {
            // set the owning side to null (unless already changed)
            if ($tag->getTagsUser() === $this) {
                $tag->setTagsUser(null);
            }
        }

        return $this;
    }




}
