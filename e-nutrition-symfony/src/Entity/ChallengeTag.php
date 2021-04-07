<?php

namespace App\Entity;

use App\Repository\ChallengeTagRepository;
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

    public function getChallenge(): ?Challenge
    {
        return $this->challenge;
    }

    public function setChallenge(?Challenge $challenge): self
    {
        $this->challenge = $challenge;

        return $this;
    }
}
