<?php

namespace App\Repository;

use App\Entity\TagEvaluation;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method TagEvaluation|null find($id, $lockMode = null, $lockVersion = null)
 * @method TagEvaluation|null findOneBy(array $criteria, array $orderBy = null)
 * @method TagEvaluation[]    findAll()
 * @method TagEvaluation[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class TagEvaluationRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, TagEvaluation::class);
    }

    // /**
    //  * @return TagEvaluation[] Returns an array of TagEvaluation objects
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
    public function findOneBySomeField($value): ?TagEvaluation
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
