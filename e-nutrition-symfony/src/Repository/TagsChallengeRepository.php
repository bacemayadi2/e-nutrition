<?php

namespace App\Repository;

use App\Entity\TagsChallenge;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method TagsChallenge|null find($id, $lockMode = null, $lockVersion = null)
 * @method TagsChallenge|null findOneBy(array $criteria, array $orderBy = null)
 * @method TagsChallenge[]    findAll()
 * @method TagsChallenge[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class TagsChallengeRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, TagsChallenge::class);
    }

    // /**
    //  * @return TagsChallenge[] Returns an array of TagsChallenge objects
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
    public function findOneBySomeField($value): ?TagsChallenge
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
