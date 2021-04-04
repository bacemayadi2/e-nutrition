<?php

namespace App\Form;

use App\Entity\Challenge;
use Doctrine\DBAL\Types\DateType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ChallengeType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('titre', TextType::class, [ 'attr'=>[ 'placeholder'=>"saisir le titre de défi"]])
            ->add('description', TextareaType::class, [ 'attr'=>[ 'placeholder'=>"saisir la description de défi"]])
            ->add('categorie', TextType::class,  [ 'attr'=>[ 'placeholder'=>"saisir la categorie de défi"]])
            ->add('dateDebut', \Symfony\Component\Form\Extension\Core\Type\DateType::class, [ 'placeholder' => ['year' => 'Year', 'month' => 'Month', 'day' => 'Day'],
                'widget' => 'choice','years' => range(2021,2050),'format' => 'dd-MM-yyyy' ])
            ->add('dateFin', \Symfony\Component\Form\Extension\Core\Type\DateType::class, [ 'placeholder' => ['year' => 'Year', 'month' => 'Month', 'day' => 'Day'],
                'widget' => 'choice','years' => range(2021,2050),'format' => 'dd-MM-yyyy' ])
            ->add("Ajouter", SubmitType::class);
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Challenge::class,
        ]);
    }
}
