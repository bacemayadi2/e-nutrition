<?php

namespace App\Entity;

use App\Repository\TagSuccessStoryRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=TagSuccessStoryRepository::class)
 */
class TagSuccessStory extends Tag
{

}
