<?php

namespace App\Form;

use App\Entity\Nutritionniste;
use App\Entity\Secretaire;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\CheckboxType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\CollectionType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\IsTrue;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Validator\Constraints\NotBlank;

class NutritionnisteType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder

            ->add('email', EmailType::class, [ 'attr'=>[ 'placeholder'=>"saisir votre adresse e-mail"]])

            ->add('nom', TextType::class, [ 'attr'=>[ 'placeholder'=>"saisir votre nom"]])

            ->add('prenom', TextType::class, [ 'attr'=>[ 'placeholder'=>"saisir votre prenom"]])

            ->add('sexe', ChoiceType::class, [ 'choices'=>[ 'Choisir votre sexe'=> 0, 'Homme'=> "Homme", 'Femme'=> 'Femme' ]])

            ->add('dateNaiss', DateType::class, [ 'placeholder' => ['year' => 'Year', 'month' => 'Month', 'day' => 'Day'],
                'widget' => 'choice','years' => range(1920,2015),'format' => 'dd-MM-yyyy' ])

            ->add('tel', TextType::class, [ 'attr'=>[ 'placeholder'=>"saisir votre numéro de téléphone"]])

            ->add('ville', TextType::class, [ 'attr'=>[ 'placeholder'=>"saisir votre ville"]])

            ->add('adresse', TextType::class, [ 'attr'=>[ 'placeholder'=>"saisir votre adresse"]])

//            ->add('secretaire',CollectionType::class,[
//                'entry_type' => SecretaireType::class,
//                'allow_add' => true,
//                'entry_options' => ['label' => false],
//                'allow_delete' => true,
//                'by_reference' => false],
//
//            )
            ->add('secretaire' )

            ->add('plainPassword', PasswordType::class, [
                // instead of being set onto the object directly,
                // this is read and encoded in the controller
                'mapped' => false,
                'constraints' => [
                    new NotBlank([
                        'message' => 'Please enter a password',
                    ]),
                    new Length([
                        'min' => 6,
                        'minMessage' => 'Your password should be at least {{ limit }} characters',
                        // max length allowed by Symfony for security reasons
                        'max' => 4096])]]);
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Nutritionniste::class,
        ]);
    }
}
