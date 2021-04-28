/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author USER
 */
public class ReservationE {

    private int id_participation;
    private int id_evenement;
    private int id_participant;
    private Date date_reservation;

    private String titre;
    private String name;

    public ReservationE(int id_participation, int id_evenement, int id_participant) {
        this.id_participation = id_participation;
        this.id_evenement = id_evenement;
        this.id_participant = id_participant;
    }

    public ReservationE(int id_participation, int id_evenement, int id_participant, Date date_reservation, String titre, String name) {
        this.id_participation = id_participation;
        this.id_evenement = id_evenement;
        this.id_participant = id_participant;
        this.date_reservation = date_reservation;
        this.titre = titre;
        this.name = name;
    }

    public ReservationE() {
    }

    public ReservationE(int id_participation, String titre, String name) {
        this.id_participation = id_participation;
        this.titre = titre;
        this.name = name;
    }

    public int getId_participation() {
        return id_participation;
    }

    public void setId_participation(int id_participation) {
        this.id_participation = id_participation;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public int getId_participant() {
        return id_participant;
    }

    public void setId_participant(int id_participant) {
        this.id_participant = id_participant;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

}
