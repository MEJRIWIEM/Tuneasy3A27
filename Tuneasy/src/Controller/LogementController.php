<?php

namespace App\Controller;

use App\Entity\Logement;
use App\Form\LogementType;
use App\Repository\LogementRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;

class LogementController extends AbstractController
{
    /**
     * @Route("/logement", name="logement")
     */
    public function index(): Response
    {
        return $this->render('logement/index.html.twig', [
            'controller_name' => 'LogementController',
        ]);
    }



    /**
     * @param LogementRepository $repo
     * @return Response
     * @Route("/AfficheLfront",name="AfficheLfront")
     */
    public function Affiche(LogementRepository $repo){
        //$repo=$this->getDoctrine()->getRepository(Logement::class);
        $logement=$repo->findAll();
        return $this->render('logement/index.html.twig',['logement'=>$logement]);
    }
    /**
     * @param LogementRepository $repo
     * @return Response
     * @Route("/modehote",name="modehote")
     */
    public function Affiche_hote(LogementRepository $repo){
        $logement=$repo->findAll();
        return $this->render('logement/hote.html.twig',['logement'=>$logement]);
    }

    /**
     * @param $id
     * @param LogementRepository $repo
     * @Route("/afficherdetails/{id}", name="afficherdetails")
     *
     */
    public function Affiche_details($id, LogementRepository $repo)
    {
        $logement=$repo->find($id);
        return $this->render('logement/logementdetails.html.twig',['logement'=>$logement]);

    }

    /**
     * @param LogementRepository $repo
     * @return Response
     * @Route("/adminAfficher",name="admin_afficher")
     */

    public function Affiche_Admin(LogementRepository $repo )
    {
        $logement=$repo->findAll();
        return $this->render('logement/adminafficher.html.twig',['logement'=>$logement]);
    }
    /**
     * @Route("/admin", name="admin")
     */
    public function Admin(): Response
    {
        return $this->render('logement/admin.html.twig', [
            'controller_name' => 'LogementController',
        ]);
    }


    /**
     * @param $id
     * @param LogementRepository $repo
     * @return \Symfony\Component\HttpFoundation\RedirectResponse
     * @Route("/delete_logement/{id}", name="delete_logement")
     */
    public function Delete_logement($id, LogementRepository $repo)
    {
       $logement=$repo->find($id);
       $em=$this->getDoctrine()->getManager();
       $em->remove($logement);
       $em->flush();
       return $this->redirectToRoute('modehote');
    }

    /**
     * @param $id
     * @param LogementRepository $repo
     * @return \Symfony\Component\HttpFoundation\RedirectResponse
     * @Route("/delete_logement_admin/{id}", name="delete_logementadmin")
     */

    public function Delete_logement_admin($id, LogementRepository $repo)
    {
        $logement=$repo->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($logement);
        $em->flush();
        return $this->redirectToRoute('admin_afficher');
    }




    /**
     * @param Request $request
     * @return Response
     * @Route("/add_logement",name="add_logement")
     */

    public function Add_logement(Request $request)
    {
        $logement = new Logement();
        $form = $this->createForm(LogementType::class,$logement);
        $form->add('Add',SubmitType::class);
        $form->handleRequest($request);


        if($form->isSubmitted() && $form->isValid())
        {
            $em=$this->getDoctrine()->getManager();
            $em->persist($logement);
            $em->flush();
            return $this->redirectToRoute('modehote');
        }
        return $this->render('logement/Add.html.twig',['form'=>$form->createView()]);


    }

    /**
     * @param LogementRepository $repo
     * @param $id
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|Response
     * @Route("/update_logement/{id}",name="update")
     */
    public function Update_logement(LogementRepository $repo ,$id, Request $request)
    {
        $logement=$repo->find($id);
        $form=$this->createForm(LogementType::class,$logement);
        $form->add('Modifier',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('modehote');
        }
        return $this->render('logement/Update.html.twig',['form'=>$form->createView()]);

    }
}
