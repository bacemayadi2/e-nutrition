<?php

namespace App\Form;

use App\Entity\EtapeDePreparation;
use phpDocumentor\Reflection\Types\Null_;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class EtapeDePreparationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('duree')
            ->add('description' ,TextareaType::class,[ 'attr'=> ['class'=> 'form-control' ,'type' => 'text' , 'name'=>'ajouter', 'rows'=> '3' ] ] )
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => EtapeDePreparation::class,
        ]);
    }
}
