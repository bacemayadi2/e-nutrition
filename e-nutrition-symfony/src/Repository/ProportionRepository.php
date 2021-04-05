<?php

namespace App\Repository;

use App\Entity\Proportion;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Proportion|null find($id, $lockMode = null, $lockVersion = null)
 * @method Proportion|null findOneBy(array $criteria, array $orderBy = null)
 * @method Proportion[]    findAll()
 * @method Proportion[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ProportionRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Proportion::class);
    }
    public function findbetwen2date($SDate,$EDate){
        return $this->createQueryBuilder('date')
            ->where('Proportion.date BETWEEN :d1 AND :d2 ')
            ->setParameter('d1',$SDate)
            ->setParameter('d1',$EDate)
            ->getQuery()
            ->getResult();
    }




    // /**
    //  * @return Proportion[] Returns an array of Proportion objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('p.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Proportion
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
