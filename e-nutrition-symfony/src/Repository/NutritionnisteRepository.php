<?php

namespace App\Repository;

use App\Entity\Nutritionniste;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Nutritionniste|null find($id, $lockMode = null, $lockVersion = null)
 * @method Nutritionniste|null findOneBy(array $criteria, array $orderBy = null)
 * @method Nutritionniste[]    findAll()
 * @method Nutritionniste[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class NutritionnisteRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Nutritionniste::class);
    }


    public function findByName($nom)
    {
        return $this->createQueryBuilder('user')
            ->where('user.nom LIKE :nom')
            ->setParameter('nom', '%'.$nom.'%')
            ->getQuery()
            ->getResult();
    }

    // /**
    //  * @return Nutritionniste[] Returns an array of Nutritionniste objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('n')
            ->andWhere('n.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('n.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Nutritionniste
    {
        return $this->createQueryBuilder('n')
            ->andWhere('n.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
