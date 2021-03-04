<?php

namespace App\Entity;

use App\Repository\ClientsRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=ClientsRepository::class)
 */
class Clients
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(name="id_client",type="integer")
     */
    private $id_client;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $nom;

    /**
     * @ORM\OneToMany(targetEntity=ReservationPlats::class, mappedBy="clientId")
     */
    private $reservationPlats;

    public function __construct()
    {
        $this->reservationPlats = new ArrayCollection();
    }

    public function setClientId(int $id): self
    {
        $this->id_client = $id;

        return $this;
    }
    public function setClient_Id(int $id): self
    {
        $this->id_client = $id;

        return $this;
    }
    public function setId_client(int $id): self
    {
        $this->id_client = $id;

        return $this;
    }
    public function getId_client(): ?int
    {
        return $this->id_client;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function __toString()
    {
        return (string)$this->getId_client();
    }


    /**
     * @return Collection|ReservationPlats[]
     */
    public function getReservationPlats(): Collection
    {
        return $this->reservationPlats;
    }




}
