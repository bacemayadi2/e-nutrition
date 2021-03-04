<?php

namespace App\Entity;

use App\Repository\PlatRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=PlatRepository::class)
 */
class Plat extends Nourriture
{


    /**
     * @ORM\Column(type="string", length=1000)
     */
    private $description;

    /**
     * @ORM\Column(type="integer")
     */
    private $nbrportion;



    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }




    public function getNbrportion(): ?int
    {
        return $this->nbrportion;
    }

    public function setNbrportion(int $nbrportion): self
    {
        $this->nbrportion = $nbrportion;

        return $this;
    }
}
