<?php

namespace App\Form;

use App\Entity\Comments;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\CheckboxType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\HiddenType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use FOS\CKEditorBundle\Form\Type\CKEditorType;

class CommentsType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('content', CKEditorType::class ,[

                'attr' => [
                    'class' => 'form-control'
                ]
            ])

            ->add('email' , EmailType::class ,[

                'attr' => [
                    'class' => 'form-control'
                ]
            ])
            ->add('nickname' , TextType::class ,[

                'attr' => [
                    'class' => 'form-control'
                ]
            ])
            ->add('parentid' , HiddenType::class, [
                'mapped'=> false
            ] )
            ->add('rgpd', CheckboxType::class)
            ->add('envoyer' , SubmitType::class)


        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Comments::class,
        ]);
    }
}
