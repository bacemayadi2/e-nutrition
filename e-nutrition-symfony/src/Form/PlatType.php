<?php

namespace App\Form;

use App\Entity\Plat;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\CollectionType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class PlatType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nom')
            ->add('description',TextareaType::class)
            ->add('nbrportion')
            ->add('etapeDePreparation', CollectionType::class  ,[
            'entry_type' => EtapeDePreparationType::class,
                'allow_add' => true,
            ])
            ->add('compostions',CollectionType::class ,[
            'entry_type' => CompositionType::class,
            'allow_add' => false,

    ] ) ;
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Plat::class,
        ]);
    }
}