<?php

namespace App\Entity;

use App\Repository\TagUtilisateurRepository;
use Doctrine\ORM\Mapping as ORM;
use Serializable;


/**
 * @ORM\Entity(repositoryClass=TagUtilisateurRepository::class)
 */
class TagUtilisateur extends Tag implements Serializable
{
    /**
     * @ORM\ManyToOne(targetEntity=Utilisateur::class, inversedBy="tagUtilisateur")
     */
    private $utilisateur;

    /**
     * @ORM\Column(type="boolean")
     */
    private $isPhotoDeProfile=false;


    private $tagsUser;

    public function getUtilisateur(): ?Utilisateur
    {
        return $this->utilisateur;
    }

    public function setUtilisateur(?Utilisateur $utilisateur): self
    {
        $this->utilisateur = $utilisateur;

        return $this;
    }

    public function getIsPhotoDeProfile(): ?bool
    {
        return $this->isPhotoDeProfile;
    }

    public function setIsPhotoDeProfile(bool $isPhotoDeProfile): self
    {
        $this->isPhotoDeProfile = $isPhotoDeProfile;

        return $this;
    }

    public function getTagsUser(): ?TagsUser
    {
        return $this->tagsUser;
    }

    public function setTagsUser(?TagsUser $tagsUser): self
    {
        $this->tagsUser = $tagsUser;

        return $this;
    }

    public function serialize()
    {

        return serialize($this->getId());
    }

    public function unserialize($serialized)
    {
        // TODO: Implement unserialize() method.
    }
}
