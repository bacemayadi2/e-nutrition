<?php

namespace App\Entity;

use App\Repository\TagSuccessStoryRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=TagSuccessStoryRepository::class)
 */
class TagSuccessStory extends Tag
{
    /**
     * @ORM\ManyToOne(targetEntity=SuccessStory::class, inversedBy="tagSucess")
     */
    private $successStory;

    public function getSuccessStory(): ?SuccessStory
    {
        return $this->successStory;
    }

    public function setSuccessStory(?SuccessStory $successStory): self
    {
        $this->successStory = $successStory;

        return $this;
    }
}
