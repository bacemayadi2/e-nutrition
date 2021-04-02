<?php

namespace App\Entity;

use App\Repository\TagBlogPostRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=TagBlogPostRepository::class)
 */
class TagBlogPost extends Tag
{
    /**
     * @ORM\ManyToOne(targetEntity=BlogPost::class, inversedBy="tagBlogPost")
     */
    private $blogPost;

    public function getBlogPost(): ?BlogPost
    {
        return $this->blogPost;
    }

    public function setBlogPost(?BlogPost $blogPost): self
    {
        $this->blogPost = $blogPost;

        return $this;
    }
}
