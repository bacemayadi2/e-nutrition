<?php

namespace App\Entity;

use App\Repository\UserRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=UserRepository::class)
 * @ORM\InheritanceType("JOINED")
 */
class Utilisateur
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Saisir votre nom svp !!!")
     */
    private $nom;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Saisir votre prénom svp !!!")
     */
    private $prenom;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Choisir votre sexe svp !!!")
     */
    private $sexe;

    /**
     * @ORM\Column(type="date")
     * @Assert\NotBlank(message="Saisir votre date de naissance svp !!!")
     */
    private $dateNaiss;

    /**
     * @ORM\Column(type="string", length=30)
     * @Assert\NotBlank(message="E-mail is required")
     * @Assert\Email(message="Saisir votre E-mail svp !!!")
     */
    private $email;

    /**
     * @ORM\Column(type="string", length=20)
     * @Assert\NotBlank(message="Saisir votre numéro de téléphone svp !!!")
     */
    private $tel;

    /**
     * @ORM\Column(type="string", length=20)
     */
    private $typeCompte;

    /**
     * @ORM\Column(type="string", length=50)
     * @Assert\NotBlank(message="Saisir votre ville svp !!!")
     */
    private $ville;

    /**
     * @ORM\Column(type="string", length=100)
     * @Assert\NotBlank(message="Saisir votre adresse svp !!!")
     */
    private $adresse;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): self
    {
        $this->prenom = $prenom;

        return $this;
    }

    public function getSexe(): ?string
    {
        return $this->sexe;
    }

    public function setSexe(string $sexe): self
    {
        $this->sexe = $sexe;

        return $this;
    }

    public function getDateNaiss(): ?\DateTimeInterface
    {
        return $this->dateNaiss;
    }

    public function setDateNaiss(\DateTimeInterface $dateNaiss): self
    {
        $this->dateNaiss = $dateNaiss;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

    public function getTel(): ?string
    {
        return $this->tel;
    }

    public function setTel(string $tel): self
    {
        $this->tel = $tel;

        return $this;
    }

    public function getTypeCompte(): ?string
    {
        return $this->typeCompte;
    }

    public function setTypeCompte(string $typeCompte): self
    {
        $this->typeCompte = $typeCompte;

        return $this;
    }

    public function getVille(): ?string
    {
        return $this->ville;
    }

    public function setVille(string $ville): self
    {
        $this->ville = $ville;

        return $this;
    }

    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    public function setAdresse(string $adresse): self
    {
        $this->adresse = $adresse;

        return $this;
    }
}
