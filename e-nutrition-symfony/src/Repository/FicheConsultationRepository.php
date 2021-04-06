<?php

namespace App\Repository;

use App\Entity\FicheConsultation;
use App\Entity\Patient;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method FicheConsultation|null find($id, $lockMode = null, $lockVersion = null)
 * @method FicheConsultation|null findOneBy(array $criteria, array $orderBy = null)
 * @method FicheConsultation[]    findAll()
 * @method FicheConsultation[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class FicheConsultationRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, FicheConsultation::class);
    }

    // /**
    //  * @return FicheConsultation[] Returns an array of FicheConsultation objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('f')
            ->andWhere('f.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('f.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?FicheConsultation
    {
        return $this->createQueryBuilder('f')
            ->andWhere('f.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
/*
 * @return void
 */
public function countByDate(){
$query=$this->createQueryBuilder('d')
    ->select('SUBSTRING(d.CreationDate,1,7) as CreationDate,COUNT(d) as count')

    ->groupBy('CreationDate')
    ->orderBy('CreationDate')
    ;
return $query->getQuery()->getResult();

}

    public function countByDateAndNutritionniste($n){
        $query=$this->createQueryBuilder('d')
            ->select('SUBSTRING(d.CreationDate,1,7) as CreationDate,COUNT(d) as count')
            ->groupBy('CreationDate')
            ->where('d.nutritionniste = :n')
            ->setParameter('n',$n)
            ->orderBy('CreationDate')
        ;
        return $query->getQuery()->getResult();

    }



    /**
     * @return FicheConsultation[] Returns an array of FicheConsultation objects
     */
    public function findEntitiesByString($value)
    {
        return $this->createQueryBuilder('f')
            ->where('f.Symptome like :v')
            ->setParameter('v', '%'.$value.'%')
            ->orderBy('f.CreationDate', 'ASC')
            ->getQuery()
            ->getResult();
    }


    public function findEntitiesByStringAndNutritionniste($value,$n)
    {
        return $this->createQueryBuilder('f')
            ->where('f.Symptome like :v and f.nutritionniste = :n')
            ->setParameter('n',$n)
            ->setParameter('v', '%'.$value.'%')
            ->orderBy('f.CreationDate', 'ASC')
            ->getQuery()
            ->getResult()
            ;
    }





}
