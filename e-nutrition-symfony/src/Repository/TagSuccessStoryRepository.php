<?php

namespace App\Repository;

use App\Entity\TagSuccessStory;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method TagSuccessStory|null find($id, $lockMode = null, $lockVersion = null)
 * @method TagSuccessStory|null findOneBy(array $criteria, array $orderBy = null)
 * @method TagSuccessStory[]    findAll()
 * @method TagSuccessStory[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class TagSuccessStoryRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, TagSuccessStory::class);
    }

    // /**
    //  * @return TagSuccessStory[] Returns an array of TagSuccessStory objects
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
    public function findOneBySomeField($value): ?TagSuccessStory
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
