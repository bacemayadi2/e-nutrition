<?php

namespace App\Form;

use App\Entity\EtapeDePreparation;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class EtapeDePreparationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('ordre')
            ->add('duree')
            ->add('description')
            ->add('plat')
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => EtapeDePreparation::class,
        ]);
    }
}
