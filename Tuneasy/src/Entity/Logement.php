<?php

namespace App\Entity;

use App\Repository\LogementRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=LogementRepository::class)
 */
class Logement
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="le titre est vide ")
     */
    private $titre;

    /**
     * @ORM\Column(type="text")
     * @Assert\NotBlank(message="la description est vide ")
     * @Assert\Length(min=5,max=500,minMessage="la description est moins de 5 caractères ",maxMessage="la description a depasse 500 caractères")
     */
    private $description;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="le type est vide ")
     */
    private $type_logement;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="l'adresse'est vide ")
     */
    private $adresse;

    /**
     * @ORM\Column(type="string", length=100)
     * @Assert\NotBlank(message="la ville est vide ")
     */
    private $ville;

    /**
     * @ORM\Column(type="integer")
     * @Assert\NotBlank(message="champs nombre de chambres est vide ")
     */
    private $n_chambres;

    /**
     * @ORM\Column(type="integer")
     * @Assert\NotBlank(message="champs nombre de lits est vide ")
     */
    private $n_lits;

    /**
     * @ORM\Column(type="integer")
     * @Assert\NotBlank(message="champs nombre de salles de bains est vide ")
     */
    private $n_salles_de_bains;

    /**
     * @ORM\Column(type="float")
     * @Assert\NotBlank(message="prix nuit est vide ")
     */
    private $prix_nuit;

    /**
     * @ORM\Column(type="boolean")
     */
    private $animaux_acceptes;

    /**
     * @ORM\Column(type="boolean")
     */
    private $valide;

    /**
     * @ORM\OneToMany(targetEntity=Reservation::class, mappedBy="logement",cascade={"all"},orphanRemoval=true)
     */
    private $reservations;

    public function __construct()
    {
        $this->reservations = new ArrayCollection();
    }

    public function getId_Logement(): ?int
    {
        return $this->id;
    }

    public function getTitre(): ?string
    {
        return $this->titre;
    }

    public function setTitre(string $titre): self
    {
        $this->titre = $titre;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getTypeLogement(): ?string
    {
        return $this->type_logement;
    }

    public function setTypeLogement(string $type_logement): self
    {
        $this->type_logement = $type_logement;

        return $this;
    }

    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    public function setAdresse(string $adresse): self
    {
        $this->adresse = $adresse;

        return $this;
    }

    public function getVille(): ?string
    {
        return $this->ville;
    }

    public function setVille(string $ville): self
    {
        $this->ville = $ville;

        return $this;
    }

    public function getNChambres(): ?int
    {
        return $this->n_chambres;
    }

    public function setNChambres(int $n_chambres): self
    {
        $this->n_chambres = $n_chambres;

        return $this;
    }

    public function getNLits(): ?int
    {
        return $this->n_lits;
    }

    public function setNLits(int $n_lits): self
    {
        $this->n_lits = $n_lits;

        return $this;
    }

    public function getNSallesDeBains(): ?int
    {
        return $this->n_salles_de_bains;
    }

    public function setNSallesDeBains(int $n_salles_de_bains): self
    {
        $this->n_salles_de_bains = $n_salles_de_bains;

        return $this;
    }

    public function getPrixNuit(): ?float
    {
        return $this->prix_nuit;
    }

    public function setPrixNuit(float $prix_nuit): self
    {
        $this->prix_nuit = $prix_nuit;

        return $this;
    }

    public function getAnimauxAcceptes(): ?bool
    {
        return $this->animaux_acceptes;
    }

    public function setAnimauxAcceptes(bool $animaux_acceptes): self
    {
        $this->animaux_acceptes = $animaux_acceptes;

        return $this;
    }
    public function getValide(): ?bool
    {
        return $this->valide;
    }

    public function setValide(bool $valide): self
    {
        $this->valide = $valide;

        return $this;
    }

    /**
     * @return Collection|Reservation[]
     */
    public function getReservations(): Collection
    {
        return $this->reservations;
    }

    public function addReservation(Reservation $reservation): self
    {
        if (!$this->reservations->contains($reservation)) {
            $this->reservations[] = $reservation;
            $reservation->setLogement($this);
        }

        return $this;
    }

    public function removeReservation(Reservation $reservation): self
    {
        if ($this->reservations->removeElement($reservation)) {
            // set the owning side to null (unless already changed)
            if ($reservation->getLogement() === $this) {
                $reservation->setLogement(null);
            }
        }

        return $this;
    }
}
