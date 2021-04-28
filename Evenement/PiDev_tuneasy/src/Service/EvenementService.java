/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DataBase.database;
import Entities.Evenement;
import Interface.Iservice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class EvenementService implements Iservice<Evenement> {

    private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public EvenementService() {
        cnx = database.getInstance().getConnection();

    }

    @Override
    public void ajouter(Evenement r) {
        String req = "insert into evenement (id_organisateur,titre,date_debut,date_fin,heure,ville, adresse, description,photo,nombre_vus,nombre_participants,nombre_max)  values ('" + r.getId_organisateur() + "','" + r.getTitre() + "','" + r.getDate_debut() + "','" + r.getDate_fin() + "','" + r.getHeure() + "','" + r.getVille() + "','" + r.getAdresse() + "','" + r.getDescription() + "','" + r.getPhoto() + "','" + r.getNombre_vus() + "','" + r.getNombre_participants() + "','" + r.getNombre_max() + "')";
        try {
            st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ArrayList<Evenement> getAll() {
        ArrayList<Evenement> events = new ArrayList<>();
        String req = "SELECT * FROM evenement";

        try {
            st = cnx.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                Evenement r = new Evenement();
                r.setId_organisateur(rs.getInt("id_organisateur"));
                r.setTitre(rs.getString("titre"));
                r.setAdresse(rs.getString("adresse"));

                r.setVille(rs.getString("ville"));
                r.setDescription(rs.getString("description"));
                r.setPhoto(rs.getString("photo"));
                r.setDate_debut(rs.getString("date_debut"));
                r.setDate_fin(rs.getString("date_fin"));
                r.setHeure(rs.getString("heure"));
                r.setNombre_vus(rs.getInt("nombre_vus"));
                r.setNombre_participants(rs.getInt("nombre_participants"));
                r.setApprouver(rs.getBoolean("approuver"));
                r.setNombre_max(rs.getInt("nombre_max"));

                events.add(r);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return events;
    }

    public ResultSet getEvenement() throws SQLException {
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT id_evenement,id_organisateur,titre,date_debut,date_fin,heure,ville, adresse, description,photo,approuver,nombre_vus,nombre_participants,nombre_max FROM evenement");

        return rs;
    }

    public void delete(Evenement t) throws SQLException {
        st = cnx.createStatement();
        String requeteDelete = "DELETE FROM `evenement` WHERE `id_organisateur` ='" + t.getId_organisateur()+ "';";
        st.executeUpdate(requeteDelete);
    }

    public void update(Evenement t) throws SQLException {
        st = cnx.createStatement();
        String requeteUpdate = "UPDATE `evenement` SET `titre` = '" + t.getTitre()+"',`date_debut` = '" + t.getDate_debut()+ "',`date_fin` = '" + t.getDate_fin()+ "',`heure` = '" + t.getHeure()+ "'"
                + ",`ville` = '" + t.getVille()+ "' ,`adresse` = '" + t.getAdresse()+ "' ,`description` = '" + t.getDescription()+ "' WHERE `evenement`.`id_evenement` = '" + t.getId_evenement() + "' ;";
        st.executeUpdate(requeteUpdate);
    }
      public ResultSet getEvenementById( int id) throws SQLException{
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery(
                                "SELECT id_evenement,id_organisateur,titre,date_debut,date_fin,heure,ville, adresse, description,photo,approuver,nombre_vus,nombre_participants,nombre_max FROM evenement where id_evenement = '" +id+"';");
    
       
        
        return rs;
    }

  
}
