<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class HomeController extends AbstractController
{
    /**
     * @Route("/home", name="home")
     */
    public function index(): Response
    {
        return $this->render('Front/home/Home.html.twig', [
            'controller_name' => 'HomeController',
        ]);
    }
    /**
     * @Route("/homeAdmin", name="homeAdmin")
     */
    public function indexAdmin(): Response
    {
        return $this->render('Back/home/homeAdmin.html.twig', [
            'controller_name' => 'HomeController',
        ]);
    }

}
