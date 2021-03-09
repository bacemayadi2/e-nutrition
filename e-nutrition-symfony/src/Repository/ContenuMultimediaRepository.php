<?php

namespace App\Repository;

use App\Entity\ContenuMultimedia;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method ContenuMultimedia|null find($id, $lockMode = null, $lockVersion = null)
 * @method ContenuMultimedia|null findOneBy(array $criteria, array $orderBy = null)
 * @method ContenuMultimedia[]    findAll()
 * @method ContenuMultimedia[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ContenuMultimediaRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, ContenuMultimedia::class);
    }

    // /**
    //  * @return ContenuMultimedia[] Returns an array of ContenuMultimedia objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('c.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?ContenuMultimedia
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
