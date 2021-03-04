<?php

namespace App\Controller;

use App\Entity\Plats;
use App\Form\PlatsType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Form\Extension\HttpFoundation\HttpFoundationRequestHandler;
use Doctrine\Common\Collections\ArrayCollection;

class PlatsController extends AbstractController
{


    /**
     * @Route("/platsAdmin", name="platsAdmin")
     */
    public function platsAdmin(): Response
    {
        $p=$this->getDoctrine()->getRepository(Plats::class);
        $plat=$p->findAll();
        return $this->render('Back/plats/PlatsAdmin.html.twig', array('plats'=>$plat));
    }

    /**
     * @Route("/addPlat", name="addPlat")
     */
    public function ajouterPlat(Request $request){

        $plat = new Plats();
        $form = $this->createForm(PlatsType::class,$plat);


        $form->handleRequest($request);

        if ($form->isSubmitted() and $form->isValid()) {
            $file = $plat->getPhoto();
            $filename = md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('upload_directory'),$filename);
            $plat->setPhoto($filename);

            $em =$this->getDoctrine()->getManager();
            $em->persist($plat);
            $em->flush();
            return $this->redirectToRoute('platsAdmin');
        }
        return $this->render('Back/forms/Aj_plat.html.twig', array('f' => $form->createView()));

    }
    /**
     * @Route("/supprimerPlat{id}", name="supprimerPlat")
     */
    public function supprimerPlat($id){
        $em=$this->getDoctrine()->getManager();
        $plat=$this->getDoctrine()->getRepository(Plats::class)->find($id);
        $em->remove($plat);
        $em->flush();
        return $this->redirectToRoute('platsAdmin');

    }
    /**
     * @Route("/modifierPlat{id}", name="modifierPlat")
     */
    public function modifierRestaurants(Request $request,$id){
        $em = $this->getDoctrine()->getManager();

        $plat=$em->getRepository(Plats::class)->find($id);
        $form=$this->createForm(PlatsType::class,$plat);

        $form->handleRequest($request);

        if($form->isSubmitted()){
            $file = $plat->getPhoto();
            $filename = md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('upload_directory'),$filename);
            $plat->setPhoto($filename);
            $em=$this->getDoctrine()->getManager();
            $em->persist($plat);
            $em->flush();
            return $this ->redirectToRoute('platsAdmin');
        }
        return $this->render('Back/forms/Aj_plat.html.twig', array('f' => $form->createView()));

    }
    /**
     * @Route("/plats{id}", name="plats")
     */
         public function platFront( $id): Response
    {
        $p=$this->getDoctrine()->getRepository(Plats::class);
        $plats=$p->findBy(['restoId'=> $id]);
        return $this->render('Front/plats/Plats.html.twig', array('plats'=>$plats));
    }

}
