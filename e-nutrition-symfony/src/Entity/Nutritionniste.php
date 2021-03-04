<?php

namespace App\Entity;

use App\Repository\NutritionnisteRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=NutritionnisteRepository::class)
 */
class Nutritionniste extends Utilisateur
{

}
