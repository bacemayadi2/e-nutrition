<?php

namespace App\Entity;

use App\Repository\TagUtilisateurRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=TagUtilisateurRepository::class)
 */
class TagUtilisateur extends Tag
{

}
