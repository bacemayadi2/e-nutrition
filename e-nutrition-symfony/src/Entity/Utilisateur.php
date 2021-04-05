<?php

namespace App\Entity;

use App\Repository\UserRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Security\Core\User\UserInterface;

/**
 * @ORM\Entity(repositoryClass=UserRepository::class)
 * @ORM\InheritanceType("JOINED")
 * @UniqueEntity(fields={"email"}, message="There is already an account with this email")
 */
class Utilisateur implements UserInterface
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    protected $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Saisir votre nom svp !!!")
     */
    protected $nom;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Saisir votre prénom svp !!!")
     */
    protected $prenom;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Choisir votre sexe svp !!!")
     */
    protected $sexe;

    /**
     * @ORM\Column(type="date")
     * @Assert\NotBlank(message="Saisir votre date de naissance svp !!!")
     */
    protected $dateNaiss;

    /**
     * @ORM\Column(type="string", length=30)
     * @Assert\NotBlank(message="E-mail is required")
     * @Assert\Email(message="Saisir votre E-mail svp !!!")
     */
    protected $email;

    /**
     * @ORM\Column(type="string", length=20)
     * @Assert\NotBlank(message="Saisir votre numéro de téléphone svp !!!")
     */
    protected $tel;

    /**
     * @ORM\Column(type="string", length=20)
     */
    protected $typeCompte;

    /**
     * @ORM\Column(type="string", length=50)
     * @Assert\NotBlank(message="Saisir votre ville svp !!!")
     */
    protected $ville;

    /**
     * @ORM\Column(type="string", length=100)
     * @Assert\NotBlank(message="Saisir votre adresse svp !!!")
     */
    protected $adresse;

    /**
     * @ORM\Column(type="string", length=255)
     */
    protected $password;

    /**
     * @ORM\Column(type="boolean")
     */
    protected $isVerified = false;

    /**
     * @ORM\Column(type="json")
     */
    protected $roles = [];

    /**
     * @ORM\OneToMany(targetEntity=TagUtilisateur::class, mappedBy="utilisateur", cascade={"all"},orphanRemoval=true)
     */
    protected $tagUtilisateur;

    public function __construct()
    {
        $this->tagUtilisateur = new ArrayCollection();
    }

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

    public function addRole(Utilisateur $user, String $role): self
    {
        array_push($user->roles, $role);
    }

//______________________________________________________________________________________________________________________

    public function getRoles(): array
    {
        $roles = $this->roles;
        // guarantee every user at least has ROLE_USER
        $roles[] = 'ROLE_USER';

        return array_unique($roles);
    }

    public function getPassword()
    {
        return $this->password;
    }

    public function getSalt()
    {
        // TODO: Implement getSalt() method.
    }

    public function getUsername()
    {
        return $this->email;
    }

    public function eraseCredentials()
    {
        // TODO: Implement eraseCredentials() method.
    }

    public function setPassword(string $password): self
    {
        $this->password = $password;

        return $this;
    }

    public function isVerified(): bool
    {
        return $this->isVerified;
    }

    public function setIsVerified(bool $isVerified): self
    {
        $this->isVerified = $isVerified;
        return $this;
    }

    public function setRoles(array $roles): self
    {
        $this->roles = $roles;

        return $this;
    }

    public function removeRoleAdmin(): self
    {
        foreach ($this->roles as $role)
        {
            if ($role == 'ROLE_ADMIN')
            {
                unset($role);
            }
        }


        return $this;
    }


    /**
     * @return Collection|TagUtilisateur[]
     */
    public function getTagUtilisateur(): Collection
    {
        return $this->tagUtilisateur;
    }

    public function addTagUtilisateur(TagUtilisateur $tagUtilisateur): self
    {
        if (!$this->tagUtilisateur->contains($tagUtilisateur)) {
            $this->tagUtilisateur[] = $tagUtilisateur;
            $tagUtilisateur->setUtilisateur($this);
        }

        return $this;
    }

    public function removeTagUtilisateur(TagUtilisateur $tagUtilisateur): self
    {
        if ($this->tagUtilisateur->removeElement($tagUtilisateur)) {
            // set the owning side to null (unless already changed)
            if ($tagUtilisateur->getUtilisateur() === $this) {
                $tagUtilisateur->setUtilisateur(null);
            }
        }

        return $this;
    }

    public function getphotoProfile():?string
    {
        if($this->tagUtilisateur != null)
        {
            foreach ($this->tagUtilisateur as $tag) {
                if ($tag->getIsPhotoDeProfile()) {
                    return $tag->getUrl();
                }
            }
        }
        return "multimedia/defaultprofile.jpg";
    }
}
