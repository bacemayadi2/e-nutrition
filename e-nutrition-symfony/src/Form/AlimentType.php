<?php

namespace App\Form;

use App\Entity\Aliment;
use AppBundle\Form\Type\TagType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\CallbackTransformer;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
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
            ->add('categorieAliment')

        ;
      /*  $builder->get('categorieAliment')
            ->addModelTransformer(new CallbackTransformer(
                function ($categorieAliment) {
                    // transform the array to a string
                    return ( $categorieAliment);
                },
                function ($categorieAliment) {
                    $i=0;
                    foreach ($categorieAliment as $c )
                    {
                        $categorieAliment[$i][0]="sssss";
                       $i++;
                    }


                    // transform the string back to an array
                    return ($categorieAliment);
                }));*/
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Aliment::class,
        ]);
    }

}
