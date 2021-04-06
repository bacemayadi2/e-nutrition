<?php

namespace App\Entity;

use App\Repository\BlogPostRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups as Groups;

/**
 * @ORM\Entity(repositoryClass=BlogPostRepository::class)
 */
class BlogPost
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     *  @Groups ("blogs")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     *  @Groups ("blogs")
     */
    private $title;

    /**
     * @ORM\Column(type="string", length=255)
     * @Groups ("blogs")
     */
    private $body;

    /**
     * @ORM\Column(type="datetime")
     * @Groups ("blogs")
     */
    private $date;

    /**
     * @ORM\OneToMany(targetEntity=TagBlogPost::class, mappedBy="blogPost", cascade={"all"},orphanRemoval=true )
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

    public function getFirstImage():?string
    {
        foreach ($this->tagBlogPost as $tag)
        {
            if ($tag->isImage())
            {
                return $tag->getUrl();
            }
        }
        return null;
    }
}
