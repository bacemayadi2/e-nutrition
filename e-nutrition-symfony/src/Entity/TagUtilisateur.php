<?php

namespace App\Entity;

use App\Repository\TagUtilisateurRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=TagUtilisateurRepository::class)
 */
class TagUtilisateur extends Tag
{
    /**
     * @ORM\ManyToOne(targetEntity=Utilisateur::class, inversedBy="tagUtilisateur")
     */
    private $utilisateur;

    public function getUtilisateur(): ?Utilisateur
    {
        return $this->utilisateur;
    }

    public function setUtilisateur(?Utilisateur $utilisateur): self
    {
        $this->utilisateur = $utilisateur;

        return $this;
    }
}
