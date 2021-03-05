<?php

namespace App\Form;

use App\Entity\Utilisateur;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\TextType;

class UserType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('typeCompte', ChoiceType::class, ['choices'  => ['Choisir votre type de compte'=> 0, 'Patient' => 1, 'Nutritionniste' => 2, 'Secrétaire' => 3]])
            ->add('nom', TextType::class, [ 'attr'=>[ 'placeholder'=>"saisir votre nom"]])
            ->add('prenom', TextType::class, [ 'attr'=>[ 'placeholder'=>"saisir votre prenom"]])
            ->add('sexe', TextType::class, [ 'attr'=>[ 'placeholder'=>"saisir votre sexe"]])
            ->add('dateNaiss', DateType::class)
            ->add('email', EmailType::class, [ 'attr'=>[ 'placeholder'=>"saisir votre adresse e-mail"]])
            ->add('tel', TextType::class, [ 'attr'=>[ 'placeholder'=>"saisir votre numéro de téléphone"]]);
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Utilisateur::class,
        ]);
    }
}
