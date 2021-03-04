<?php

namespace App\Controller;

use App\Entity\Plats;
use App\Entity\Restaurants;
use App\Form\PlatsType;
use App\Form\RestaurantsType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Form\Extension\HttpFoundation\HttpFoundationRequestHandler;
use Doctrine\Common\Collections\ArrayCollection;

class RestaurantsController extends AbstractController
{
    /**
     * @Route("/restaurants", name="restaurants")
     */
    public function index(): Response
    {
        $r=$this->getDoctrine()->getRepository(Restaurants::class);
        $restaurant=$r->findAll();
        return $this->render('Front/restaurants/Restaurants.html.twig', array('restaurants'=>$restaurant));


    }
    /**
     * @Route("/detailRest{id}", name="detailRest")
     */
    public function readRestaurant(int $id): Response
    {

        $r=$this->getDoctrine()->getRepository(Restaurants::class);
        $restaurant=$r->find($id);
        return $this->render('Front/restaurants/det.html.twig', array('restaurant'=>$restaurant));

    }
    /**
     * @Route("/restaurantsAdmin", name="restaurantsAdmin")
     */
    public function restaurantAdmin(): Response
    {
        return $this->render('Back/restaurants/restaurantsAdmin.html.twig', [
            'controller_name' => 'RestaurantsController',
        ]);

    }
    /**
     * @Route("/ajouterRestaurant", name="ajouteRestaurants")
     */
    public function ajouterRestaurants(Request $request){

        $restaurant = new Restaurants();
        $form = $this->createForm(RestaurantsType::class,$restaurant);


        $form->handleRequest($request);

        if ($form->isSubmitted() and $form->isValid()) {
            $file = $restaurant->getPhoto();
            $filename = md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('upload_directory'),$filename);
            $restaurant->setPhoto($filename);
            $em =$this->getDoctrine()->getManager();
            $em->persist($restaurant);
            $em->flush();
            return $this->redirectToRoute('restaurantsAdmin');
        }
        return $this->render('Back/forms/Aj_resto.html.twig', array('f' => $form->createView()));

    }

    /**
     * @Route("/modifierRestaurant{id}", name="modifierRestaurant")
     */
        public function modifierRestaurants(Request $request,$id){
            $em = $this->getDoctrine()->getManager();

            $restaurant=$em->getRepository(Restaurants::class)->find($id);
            $form=$this->createForm(RestaurantsType::class,$restaurant);

            $form->handleRequest($request);

            if($form->isSubmitted()and $form->isValid()){
                $file = $restaurant->getPhoto();
                $filename = md5(uniqid()).'.'.$file->guessExtension();
                $file->move($this->getParameter('upload_directory'),$filename);
                $em=$this->getDoctrine()->getManager();
                $em->persist($restaurant);
                $em->flush();
                return $this ->redirectToRoute('restaurantsAdmin');
            }
            return $this->render('Back/forms/Aj_resto.html.twig', array('f' => $form->createView()));

        }
    /**
     * @Route("/restaurantsAdmin", name="restaurantsAdmin")
     */
        public function afficherRestaurant(){
            $r=$this->getDoctrine()->getRepository(Restaurants::class);
            $restaurant=$r->findAll();
            return $this->render('Back/restaurants/restaurantsAdmin.html.twig', array('restaurants'=>$restaurant));


        }
    /**
     * @Route("/supprimerRestaurant{id}", name="supprimerRestaurant")
     */
        public function supprimerRestaurant($id){
            $em=$this->getDoctrine()->getManager();
            $restaurant=$this->getDoctrine()->getRepository(Restaurants::class)->find($id);
            $em->remove($restaurant);
            $em->flush();
            return $this->redirectToRoute('restaurantsAdmin');

        }





}
