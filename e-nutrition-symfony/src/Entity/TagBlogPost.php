<?php

namespace App\Entity;

use App\Repository\TagBlogPostRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=TagBlogPostRepository::class)
 */
class TagBlogPost extends Tag
{

}
