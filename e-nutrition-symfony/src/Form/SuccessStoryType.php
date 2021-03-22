<?php

namespace App\Form;

use App\Entity\SuccessStory;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\CollectionType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;


class SuccessStoryType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('titre')
            ->add('text')

            ->add('likeStory')
            ->add('tagSucess' , CollectionType::class , [
        'entry_type'=> TagSuccessStoryType::class,
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => SuccessStory::class,
        ]);
    }
}
