<?php

namespace App\Form;

use App\Entity\Aliment;
use App\Entity\CategorieAliment;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class AlimentType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nom')
            ->add('lipides')
            ->add('glucides')
            ->add('proteines')
            ->add('poid')
            ->add('codeABarre')
            ->add('categorieAliment',EntityType::class,[
                'class'  => CategorieAliment::class,
                    'choice_label'=>'nomCategorie',
        ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Aliment::class,
        ]);
    }
}
