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
        return $this->createQueryBuilder('proportion')
            ->where('proportion.date BETWEEN :d1 AND :d2 ')
            ->setParameter('d1',$SDate)
            ->setParameter('d2',$EDate)
            ->getQuery()
            ->getResult();
    }

    public function countByDateAndpatientjour($n,$d1,$d2){
        $query=$this->createQueryBuilder('d')
            ->select('SUBSTRING(d.date,12,5) as date,sum(d.calorie) as count')
            ->groupBy('date')
            ->where('d.patient = :n and d.date BETWEEN :d1 AND :d2  and d.date >= :d3')
            ->setParameter('d1',$d1)
            ->setParameter('d2',$d2)
            ->setParameter('n',$n)
            ->setParameter('d3',date("y-m-d", time()))
            ->orderBy('date')
        ;
        return $query->getQuery()->getResult();

    }


    public function countByDateAndpatientmois($n,$d1,$d2){
        $query=$this->createQueryBuilder('d')
            ->select('SUBSTRING(d.date,7,4) as date,sum(d.calorie) as count')
            ->groupBy('date')
            ->where('d.patient = :n and d.date BETWEEN :d1 AND :d2 ')
            ->setParameter('d1',$d1)
            ->setParameter('d2',$d2)
            ->setParameter('n',$n)
            ->orderBy('date')
        ;
        return $query->getQuery()->getResult();

    }

    public function countByDateAndpatientannee($n,$d1,$d2){
        $query=$this->createQueryBuilder('d')
            ->select('SUBSTRING(d.date,1,7) as date,sum(d.calorie) as count')
            ->groupBy('date')
            ->where('d.patient = :n and d.date BETWEEN :d1 AND :d2 ')
            ->setParameter('d1',$d1)
            ->setParameter('d2',$d2)
            ->setParameter('n',$n)
            ->orderBy('date')
        ;
        return $query->getQuery()->getResult();

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
