<?php

namespace App\Entity;

use App\Repository\ReservationRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=ReservationRepository::class)
 */
class Reservation
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;



    /**
     * @ORM\Column(type="float")
     * @Assert\NotBlank(message="le champ prix total est vide ")
     */
    private $prix_total;

    /**
     * @ORM\Column(type="datetime")
     */
    private $date_check_in;

    /**
     * @ORM\Column(type="datetime")
     */
    private $date_check_out;

    /**
     * @ORM\ManyToOne(targetEntity=Logement::class, inversedBy="reservations")
     */
    private $logement;

    public function getId(): ?int
    {
        return $this->id;
    }



    public function getPrixTotal(): ?float
    {
        return $this->prix_total;
    }

    public function setPrixTotal(float $prix_total): self
    {
        $this->prix_total = $prix_total;

        return $this;
    }

    public function getDateCheckIn(): ?\DateTimeInterface
    {
        return $this->date_check_in;
    }

    public function setDateCheckIn(\DateTimeInterface $date_check_in): self
    {
        $this->date_check_in = $date_check_in;

        return $this;
    }

    public function getDateCheckOut(): ?\DateTimeInterface
    {
        return $this->date_check_out;
    }

    public function setDateCheckOut(\DateTimeInterface $date_check_out): self
    {
        $this->date_check_out = $date_check_out;

        return $this;
    }

    public function getLogement(): ?Logement
    {
        return $this->logement;
    }

    public function setLogement(?Logement $logement): self
    {
        $this->logement = $logement;

        return $this;
    }
}
