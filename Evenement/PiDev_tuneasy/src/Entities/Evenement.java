/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author USER
 */
public class Evenement {
      private int id_evenement;
    private int id_organisateur;
    private String titre;
    private String date_debut;
    private String date_fin;
    private String heure;
    private String ville;
    private String adresse;
    private String description;
    private String photo;
    private Boolean approuver; 
    private int nombre_vus;
    private int nombre_participants;
    private int nombre_max;

    public Evenement() {
    }

    public Evenement(int id_organisateur, String titre, String date_debut, String date_fin, String heure, String ville, String adresse, String description, String photo, Boolean approuver, int nombre_vus, int nombre_participants, int nombre_max) {
        this.id_organisateur = id_organisateur;
        this.titre = titre;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.heure = heure;
        this.ville = ville;
        this.adresse = adresse;
        this.description = description;
        this.photo = photo;
        this.approuver = approuver;
        this.nombre_vus = nombre_vus;
        this.nombre_participants = nombre_participants;
        this.nombre_max = nombre_max;
    }

    public Evenement(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public int getId_organisateur() {
        return id_organisateur;
    }

    public void setId_organisateur(int id_organisateur) {
        this.id_organisateur = id_organisateur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Boolean getApprouver() {
        return approuver;
    }

    public void setApprouver(Boolean approuver) {
        this.approuver = approuver;
    }

    public int getNombre_vus() {
        return nombre_vus;
    }

    public void setNombre_vus(int nombre_vus) {
        this.nombre_vus = nombre_vus;
    }

    public int getNombre_participants() {
        return nombre_participants;
    }

    public void setNombre_participants(int nombre_participants) {
        this.nombre_participants = nombre_participants;
    }

    public int getNombre_max() {
        return nombre_max;
    }

    public void setNombre_max(int nombre_max) {
        this.nombre_max = nombre_max;
    }

    public Evenement(int id_evenement, int id_organisateur, String titre, String date_debut, String date_fin, String heure, String ville, String adresse, String description, String photo, Boolean approuver, int nombre_vus, int nombre_participants, int nombre_max) {
        this.id_evenement = id_evenement;
        this.id_organisateur = id_organisateur;
        this.titre = titre;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.heure = heure;
        this.ville = ville;
        this.adresse = adresse;
        this.description = description;
        this.photo = photo;
        this.approuver = approuver;
        this.nombre_vus = nombre_vus;
        this.nombre_participants = nombre_participants;
        this.nombre_max = nombre_max;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id_evenement=" + id_evenement + ", id_organisateur=" + id_organisateur + ", titre=" + titre + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", heure=" + heure + ", ville=" + ville + ", adresse=" + adresse + ", description=" + description + ", photo=" + photo + ", approuver=" + approuver + ", nombre_vus=" + nombre_vus + ", nombre_participants=" + nombre_participants + ", nombre_max=" + nombre_max + '}';
    }
    
    
}
