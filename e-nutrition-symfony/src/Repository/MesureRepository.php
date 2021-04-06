<?php

namespace App\Repository;

use App\Entity\Mesure;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Mesure|null find($id, $lockMode = null, $lockVersion = null)
 * @method Mesure|null findOneBy(array $criteria, array $orderBy = null)
 * @method Mesure[]    findAll()
 * @method Mesure[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class MesureRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Mesure::class);
    }

    // /**
    //  * @return Mesure[] Returns an array of Mesure objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('m')
            ->andWhere('m.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('m.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Mesure
    {
        return $this->createQueryBuilder('m')
            ->andWhere('m.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */

    public function countByDate(){
        $query=$this->createQueryBuilder('d')
            ->select('SUBSTRING(d.dateMesure,1,10) as dateMesure,COUNT(d) as count')

            ->groupBy('dateMesure')
            ->orderBy('dateMesure')
        ;
        return $query->getQuery()->getResult();

    }

    public function countByDatePatient($n){
        $query=$this->createQueryBuilder('d')
            ->select('SUBSTRING(d.dateMesure,1,10) as dateMesure,COUNT(d) as count')
            ->groupBy('dateMesure')
            ->where('d.patient = :n')
            ->setParameter('n',$n)
            ->orderBy('dateMesure')
        ;
        return $query->getQuery()->getResult();

    }
}
