<?php

namespace App\Entity;

use App\Repository\ChallengeTagRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=ChallengeTagRepository::class)
 */
class ChallengeTag extends Tag
{
    /**
     * @ORM\ManyToOne(targetEntity=Challenge::class, inversedBy="tagChallenge")
     */
    private $challenge;


    private $tagsChallenge;

    /**
     * @ORM\ManyToOne(targetEntity=Utilisateur::class)
     */
    private $user;



    public function __construct()
    {
        $this->patient = new ArrayCollection();
    }

    public function getChallenge(): ?Challenge
    {
        return $this->challenge;
    }

    public function setChallenge(?Challenge $challenge): self
    {
        $this->challenge = $challenge;

        return $this;
    }

    public function getTagsChallenge(): ?TagsChallenge
    {
        return $this->tagsChallenge;
    }

    public function setTagsChallenge(?TagsChallenge $tagsChallenge): self
    {
        $this->tagsChallenge = $tagsChallenge;

        return $this;
    }

    public function getUser(): ?Utilisateur
    {
        return $this->user;
    }

    public function setUser(?Utilisateur $user): self
    {
        $this->user = $user;

        return $this;
    }



}
