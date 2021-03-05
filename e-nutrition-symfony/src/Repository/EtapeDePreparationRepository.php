<?php

namespace App\Repository;

use App\Entity\EtapeDePreparation;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method EtapeDePreparation|null find($id, $lockMode = null, $lockVersion = null)
 * @method EtapeDePreparation|null findOneBy(array $criteria, array $orderBy = null)
 * @method EtapeDePreparation[]    findAll()
 * @method EtapeDePreparation[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class EtapeDePreparationRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, EtapeDePreparation::class);
    }

    // /**
    //  * @return EtapeDePreparation[] Returns an array of EtapeDePreparation objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('e')
            ->andWhere('e.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('e.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?EtapeDePreparation
    {
        return $this->createQueryBuilder('e')
            ->andWhere('e.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
