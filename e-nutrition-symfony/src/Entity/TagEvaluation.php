<?php

namespace App\Entity;

use App\Repository\TagEvaluationRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=TagEvaluationRepository::class)
 */
class TagEvaluation extends Tag
{

}
