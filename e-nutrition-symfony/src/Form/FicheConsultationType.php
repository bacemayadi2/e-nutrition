<?php

namespace App\Form;

use App\Entity\FicheConsultation;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
class FicheConsultationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('CreationDate',DateType::class)
            ->add('Poids')
            ->add('Taille')
            ->add('Imc')
            ->add('Symptome',TextareaType::class)
            ->add('Apetit')
            ->add('Description',TextareaType::class)

        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => FicheConsultation::class,
        ]);
    }
}
