<?php

namespace App\Form;

use App\Entity\TagsUser;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\CollectionType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class TagsUserType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('tags', CollectionType::class  ,[
                'entry_type' => TagUtilisateurType::class,
                'allow_add' => true,
                'allow_delete' => true,
                'by_reference' => false,

            ]);
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => TagsUser::class,
        ]);
    }
}
