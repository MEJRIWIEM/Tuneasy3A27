<?php

namespace App\Controller;

use App\Entity\Competition;
use App\Form\CompetitionType;
use App\Repository\CompetitionRepository;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class CompetitionController extends AbstractController
{
    /**
     * @Route("/competition", name="competition")
     */
    public function index(): Response
    {
        return $this->render('competition/index.html.twig', [
            'controller_name' => 'CompetitionController',
        ]);
    }

    /**
     * @param CompetitionRepository $repo
     * @return Response
     * @Route("/afficheCompetition", name="afficheCompetition")
     */
    public function getCompetitions(CompetitionRepository $repo) {
        $competitions = $repo->findAll();

        return $this->render('competition/affiche.html.twig', ['competitions' => $competitions]);
    }

    /**
     * @param CompetitionRepository $repo
     * @param $idComp
     * @return \Symfony\Component\HttpFoundation\RedirectResponse
     * @Route("suppCompetition/{idComp}", name="suppCompetition")
     */
    public function suppCompetition(CompetitionRepository $repo, $idComp) {

        dump($idComp);
        $em = $this->getDoctrine()->getManager();
        $competitionToDelete = $repo->find($idComp);
        $em->remove($competitionToDelete);
        $em->flush();

        return $this->redirectToRoute("afficheCompetition");
    }


    /**
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|Response
     * @Route("ajoutCompetition",name="ajoutCompetition")
     */
    public function ajoutCompetition(Request $request) {
        $em = $this->getDoctrine()->getManager();
        $comp = new Competition();
        $form = $this->createForm(CompetitionType::class, $comp);
        $form->add("Ajouter", SubmitType::class, array(
            'label' => 'Ajouter',
            'attr' => array(
                'class'=> "btn btn-primary mt-3"
            )
        ));
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()) {
            $em->persist($comp);
            $em->flush();
            return $this->redirectToRoute('afficheCompetition');
        }
        return $this->render('competition/ajout.html.twig', [ "form" => $form->createView() ]);
    }

    /**
     * @param $idComp
     * @param CompetitionRepository $repo
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|Response
     * @Route("modifCompetition/{idComp}", name="modifCompetition")
     */
    public function modifCompetition($idComp, CompetitionRepository $repo, Request $request) {
        $em = $this->getDoctrine()->getManager();
        $comp = $repo->find($idComp);
        $form = $this->createForm(CompetitionType::class, $comp);

        $form->add("Modifier", SubmitType::class, array(
            'label' => 'Modifier',
            'attr' => array(
                'class'=> "btn btn-warning mt-3"
            )
        ));
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()) {
            $em->flush();
            return $this->redirectToRoute('afficheCompetition');
        }
        return $this->render('competition/ajout.html.twig', [ "form" => $form->createView() ]);
    }

}
