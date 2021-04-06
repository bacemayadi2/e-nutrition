<?php

namespace App\Form;

use App\Entity\BlogPost;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\CollectionType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class BlogPostType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder

            ->add('title')
            ->add('body', TextareaType::class, [
                'attr' => ['class' => 'form-control'],
            ])
            ->add('tagBlogPost', CollectionType::class  ,[
                'entry_type' => TagBlogPostType::class,
                'allow_add' => true,
                'entry_options' => ['label' => false],
                'allow_delete' => true,
                'by_reference' => false,

            ]);;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => BlogPost::class,
        ]);
    }
}
