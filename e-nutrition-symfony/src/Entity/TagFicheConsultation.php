<?php

namespace App\Entity;

use App\Repository\TagFicheConsultationRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * @ORM\Entity(repositoryClass=TagFicheConsultationRepository::class)
 */
class TagFicheConsultation extends Tag
{
    /**
     * @ORM\ManyToOne(targetEntity=FicheConsultation::class, inversedBy="tagFicheConsultation")
     * @Groups ("tagfiche")
     */
    private $ficheConsultation;

    public function getFicheConsultation(): ?FicheConsultation
    {
        return $this->ficheConsultation;
    }

    public function setFicheConsultation(?FicheConsultation $ficheConsultation): self
    {
        $this->ficheConsultation = $ficheConsultation;

        return $this;
    }
}
