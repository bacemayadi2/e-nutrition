<?php

namespace App\Repository;

use App\Entity\TagNourriture;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method TagNourriture|null find($id, $lockMode = null, $lockVersion = null)
 * @method TagNourriture|null findOneBy(array $criteria, array $orderBy = null)
 * @method TagNourriture[]    findAll()
 * @method TagNourriture[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class TagNourritureRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, TagNourriture::class);
    }

    // /**
    //  * @return TagNourriture[] Returns an array of TagNourriture objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('t')
            ->andWhere('t.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('t.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?TagNourriture
    {
        return $this->createQueryBuilder('t')
            ->andWhere('t.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
