<?php

namespace App\Entity;

use App\Repository\PatientRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=PatientRepository::class)
 */
class Patient
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
    private $styleDeVie;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getStyleDeVie(): ?string
    {
        return $this->styleDeVie;
    }

    public function setStyleDeVie(string $styleDeVie): self
    {
        $this->styleDeVie = $styleDeVie;

        return $this;
    }
}
