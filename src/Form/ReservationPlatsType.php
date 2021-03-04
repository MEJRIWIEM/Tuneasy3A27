<?php

namespace App\Form;

use App\Entity\Clients;
use App\Entity\Plats;
use App\Entity\ReservationPlats;

use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ReservationPlatsType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('Quantity')
            ->add('dateReservation')
            ->add('clientId',EntityType::class,[
                'class' => Clients::class,
                'choice_label'=>'nom',


            ])
            ->add('platId',EntityType::class,[
                'class' => Plats::class,
                'choice_label'=>'id_plat',


            ])
            ->add('Valider',SubmitType::class);
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => ReservationPlats::class,
        ]);
    }
}
