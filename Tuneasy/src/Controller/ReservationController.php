<?php

namespace App\Controller;

use App\Entity\Reservation;
use App\Form\ReservationType;
use App\Repository\LogementRepository;
use App\Repository\ReservationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;

class ReservationController extends AbstractController
{
    /**
     * @Route("/reservation", name="reservation")
     */
    public function index(): Response
    {
        return $this->render('reservation/index.html.twig', [
            'controller_name' => 'ReservationController',
        ]);
    }

    /**
     * @param ReservationRepository $repo
     * @return Response
     * @Route("reservation/Affiche",name="AfficheR")
     */

    public function Affiche(ReservationRepository $repo)
    {
        $reservation=$repo->findAll();
        return $this->render('reservation/index.html.twig',['reservation'=>$reservation]);
    }


    /**
     * @param Request $request
     * @param $id
     * @param LogementRepository $repo
     * @return Response
     *  @Route("/add_reservation/{id}",name="add_reservation")
     */

   function Add_Reservation (Request $request,$id ,LogementRepository $repo)
    {
        $logement=$repo->find($id);
        $reservation= new Reservation();
        $reservation->setLogement($logement);
        $form=$this->createForm(ReservationType::class,$reservation);
        $form->add('Ajouter',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid())
        {
            $em=$this->getDoctrine()->getManager();
            $em->persist($reservation);
            $em->flush();
            return $this->redirectToRoute('AfficheR');

        }
        return $this->render('reservation/AddRes.html.twig',['form'=>$form->createView()]);
    }

    /**
     * @param $id
     * @param ReservationRepository $repo
     * @return \Symfony\Component\HttpFoundation\RedirectResponse
     * @Route("deleteR/{id}",name="deleteR")
     */
    function Delete_Reservation ($id,ReservationRepository  $repo)
    {
        $reservation=$repo->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($reservation);
        $em->flush();
        return $this->redirectToRoute('AfficheR');
    }

    /**
     * @param ReservationRepository $repo
     * @param $id
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|Response
     *  @Route("updateR/{id}",name="updateR")
     */

    public function Update_Reservation(ReservationRepository $repo ,$id, Request $request)
    {
        $reservation=$repo->find($id);
        $form=$this->createForm(ReservationType::class,$reservation);
        $form->add('Update',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid())
        {
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('AfficheR');

        }
        return $this->render('reservation/UpdateRes.html.twig',['form'=>$form->createView()]);

    }
}
