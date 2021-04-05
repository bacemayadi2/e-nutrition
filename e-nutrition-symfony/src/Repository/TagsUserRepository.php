<?php

namespace App\Repository;

use App\Entity\TagsUser;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method TagsUser|null find($id, $lockMode = null, $lockVersion = null)
 * @method TagsUser|null findOneBy(array $criteria, array $orderBy = null)
 * @method TagsUser[]    findAll()
 * @method TagsUser[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class TagsUserRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, TagsUser::class);
    }

    // /**
    //  * @return TagsUser[] Returns an array of TagsUser objects
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
    public function findOneBySomeField($value): ?TagsUser
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
