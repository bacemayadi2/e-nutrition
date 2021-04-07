<?php

namespace App\Repository;

use App\Entity\ChallengeTag;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method ChallengeTag|null find($id, $lockMode = null, $lockVersion = null)
 * @method ChallengeTag|null findOneBy(array $criteria, array $orderBy = null)
 * @method ChallengeTag[]    findAll()
 * @method ChallengeTag[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ChallengeTagRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, ChallengeTag::class);
    }

    // /**
    //  * @return ChallengeTag[] Returns an array of ChallengeTag objects
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
    public function findOneBySomeField($value): ?ChallengeTag
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
