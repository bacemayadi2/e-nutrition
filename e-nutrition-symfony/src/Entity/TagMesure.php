<?php

namespace App\Entity;

use App\Repository\TagMesureRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=TagMesureRepository::class)
 */
class TagMesure extends Tag

{

}
