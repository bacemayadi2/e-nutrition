<?php

namespace App\Repository;

use App\Entity\TagBlogPost;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method TagBlogPost|null find($id, $lockMode = null, $lockVersion = null)
 * @method TagBlogPost|null findOneBy(array $criteria, array $orderBy = null)
 * @method TagBlogPost[]    findAll()
 * @method TagBlogPost[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class TagBlogPostRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, TagBlogPost::class);
    }

    // /**
    //  * @return TagBlogPost[] Returns an array of TagBlogPost objects
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
    public function findOneBySomeField($value): ?TagBlogPost
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
