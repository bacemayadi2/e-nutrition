<?php

namespace App\Entity;

use App\Repository\BlogPostRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=BlogPostRepository::class)
 */
class BlogPost
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $title;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $body;

    /**
     * @ORM\Column(type="datetime")
     */
    private $date;

    /**
     * @ORM\OneToMany(targetEntity=TagBlogPost::class, mappedBy="blogPost")
     */
    private $tagBlogPost;

    public function __construct()
    {
        $this->tagBlogPost = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getTitle(): ?string
    {
        return $this->title;
    }

    public function setTitle(string $title): self
    {
        $this->title = $title;

        return $this;
    }

    public function getBody(): ?string
    {
        return $this->body;
    }

    public function setBody(string $body): self
    {
        $this->body = $body;

        return $this;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }

    /**
     * @return Collection|TagBlogPost[]
     */
    public function getTagBlogPost(): Collection
    {
        return $this->tagBlogPost;
    }

    public function addTagBlogPost(TagBlogPost $tagBlogPost): self
    {
        if (!$this->tagBlogPost->contains($tagBlogPost)) {
            $this->tagBlogPost[] = $tagBlogPost;
            $tagBlogPost->setBlogPost($this);
        }

        return $this;
    }

    public function removeTagBlogPost(TagBlogPost $tagBlogPost): self
    {
        if ($this->tagBlogPost->removeElement($tagBlogPost)) {
            // set the owning side to null (unless already changed)
            if ($tagBlogPost->getBlogPost() === $this) {
                $tagBlogPost->setBlogPost(null);
            }
        }

        return $this;
    }
}
