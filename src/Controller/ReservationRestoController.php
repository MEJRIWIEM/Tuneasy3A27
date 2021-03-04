<?php

namespace App\Controller;

use App\Entity\ReservationPlats;
use App\Entity\Clients;
use App\Entity\Plats;
use App\Form\ReservationPlatsType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Form\Extension\HttpFoundation\HttpFoundationRequestHandler;
use Doctrine\Common\Collections\ArrayCollection;

class ReservationRestoController extends AbstractController
{
    /**
     * @Route("/reservationResto", name="reservationResto")
     */
    public function index(): Response
    {
        return $this->render('Front/reservation_resto/MesReservations.html.twig', [
            'controller_name' => 'ReservationRestoController',
        ]);
    }
    /**
     * @Route("/reservationRestoAdmin", name="reservation_resto_admin")
     */
    public function ResAdmin(): Response
    {
        $r=$this->getDoctrine()->getRepository(ReservationPlats::class);
        $reservation=$r->findAll();
        return $this->render('Back/reservation_resto/MesReservationsAdmin.html.twig', array('reservations'=>$reservation));

    }
    /**
     * @Route("/addReservationPlat{id}", name="addReservationPlat")
     */
    public function ajouterReservation(Request $request ,$id){


        $reservation = new ReservationPlats();

        $reservation->setDateReservation(new \DateTime('now'));

        $form = $this->createForm(ReservationPlatsType::class,$reservation);


        $form->handleRequest($request);

        if ($form->isSubmitted() and $form->isValid()) {


            $em =$this->getDoctrine()->getManager();
            $em->persist($reservation);
            $em->flush();
            return $this->redirectToRoute('MesReservationPlat');
        }
        return $this->render('Front/forms/Aj_reserv_plat.html.twig', array('reser' => $form->createView()));

    }
    /**
     * @Route("/MesReservationPlat", name="MesReservationPlat")
     */
    public function afficherReservationP(){
        $r=$this->getDoctrine()->getRepository(ReservationPlats::class);
        $reservation=$r->findAll();
        return $this->render('Front/reservation_resto/MesReservations.html.twig', array('reservations'=>$reservation));


    }
    /**
     * @Route("/supReservationPlat{id}", name="supReservationPlat")
     */
    public function supprReservationP($id){
        $em=$this->getDoctrine()->getManager();
        $reservation=$this->getDoctrine()->getRepository(ReservationPlats::class)->find($id);
        $em->remove($reservation);
        $em->flush();
        return $this->redirectToRoute('MesReservationPlat');

    }

    /**
     * @Route("/modReservationPlat{id}", name="modReservationPlat")
     */
    public function modifierReservationP(Request $request,$id){
        $em = $this->getDoctrine()->getManager();

        $res=$em->getRepository(ReservationPlats::class)->find($id);
        $form=$this->createForm(ReservationPlatsType::class,$res);

        $form->handleRequest($request);

        if($form->isSubmitted()){

            $em=$this->getDoctrine()->getManager();
            $em->persist($res);
            $em->flush();
            return $this ->redirectToRoute('MesReservationPlat');
        }
        return $this->render('Front/forms/Aj_reserv_plat.html.twig', array('reser' => $form->createView()));

    }
    /**
     * @Route ("deleteAdminReser{id}",name="deleteAdminReser")
     */
    public function DeleteAdminReser($id){
        $em=$this->getDoctrine()->getManager();
        $r=$this->getDoctrine()->getRepository(ReservationPlats::class)->find($id);
        $em->remove($r);
        $em->flush();
        return $this->redirectToRoute('reservation_resto_admin');
    }
}
