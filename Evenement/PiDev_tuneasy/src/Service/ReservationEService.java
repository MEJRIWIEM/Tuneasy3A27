/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DataBase.database;
import Entities.Evenement;
import Entities.ReservationE;
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
public class ReservationEService implements Iservice<ReservationE> {
    
    private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public ReservationEService() {
        cnx = database.getInstance().getConnection();

    }
    
    public void ajouter(ReservationE r) {
        String req = "insert into participation_eve (id_evenement,id_participant)  values ('" + r.getId_evenement() + "','" + r.getId_participant() + "')";
        try {
            st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public ResultSet getUserEmail(String nom) throws SQLException{
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT user.email  FROM user where user.name like '"+nom+"' ;");
                         
        //"SELECT reservation_plats.id_res_plat , reservation_plats.id_client ,reservation_plats.id_plat,reservation_plats.quantity, reservation_plats.date_reservation,user.email,user.firstname FROM reservation_plats WHERE id_res_plat ='"+id+"', LEFT JOIN ON reservation_plats.id_client = user.id;");
   //  "SELECT reservation_plats.id_res_plat , reservation_plats.id_client ,reservation_plats.id_plat,reservation_plats.quantity, reservation_plats.date_reservation,plats.nom,user.email,user.firstname FROM reservation_plats WHERE id_res_plat ='"+id+"' INNER JOIN ON reservation_plats.id_client = user.id INNER JOIN ON reservation_plats.id_plat = plats.id_plat  ");
       //"SELECT  r.id_res_plat , r.id_client ,r.id_plat, p.nom,r.quantity, r.date_reservation FROM reservation_plats as r inner join plats as p on r.id_plat = p.id_plat ");
       //"SELECT  m.id, m.nom , m.marque, m.quantite,m.image,m.id_categorie,m.id_etat,c.nom,e.type_etat FROM materiel as m left join categorie as c on m.id_categorie = c.id left join etat as e on m.id_etat=e.id";
        
        return rs;
    }
    public ResultSet getTitre(int id) throws SQLException{
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT titre FROM evenement WHERE id_evenement ='"+id+"';");
                         
        //"SELECT reservation_plats.id_res_plat , reservation_plats.id_client ,reservation_plats.id_plat,reservation_plats.quantity, reservation_plats.date_reservation,user.email,user.firstname FROM reservation_plats WHERE id_res_plat ='"+id+"', LEFT JOIN ON reservation_plats.id_client = user.id;");
   //  "SELECT reservation_plats.id_res_plat , reservation_plats.id_client ,reservation_plats.id_plat,reservation_plats.quantity, reservation_plats.date_reservation,plats.nom,user.email,user.firstname FROM reservation_plats WHERE id_res_plat ='"+id+"' INNER JOIN ON reservation_plats.id_client = user.id INNER JOIN ON reservation_plats.id_plat = plats.id_plat  ");
       //"SELECT  r.id_res_plat , r.id_client ,r.id_plat, p.nom,r.quantity, r.date_reservation FROM reservation_plats as r inner join plats as p on r.id_plat = p.id_plat ");
       //"SELECT  m.id, m.nom , m.marque, m.quantite,m.image,m.id_categorie,m.id_etat,c.nom,e.type_etat FROM materiel as m left join categorie as c on m.id_categorie = c.id left join etat as e on m.id_etat=e.id";
        
        return rs;
    }
   
    /**
     *
     * @return
     */
    @Override
    public ArrayList<ReservationE> getAll() {
        ArrayList<ReservationE>  event = new ArrayList<>();
        String req = "SELECT * FROM participation_eve";

        try {
            st = cnx.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
               ReservationE r = new ReservationE();
                                r.setId_participation(rs.getInt("id_participation "));

               r.setId_participant(rs.getInt("id_participant"));
                              r.setId_evenement(rs.getInt("id_evenement"));

              
                event.add(r);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return event;
    }
     public ResultSet getReservationE22() throws SQLException {
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery("SELECT id_participation , id_participant, id_evenement FROM participation_eve ;");
         //     "SELECT participation_eve.id_participation ,participation_eve.id_evenement,participation_eve.id_participant, evenement.titre, user.firstname FROM participation_eve INNER JOIN evenement ON evenement.id_evenement=participation_eve.id_evenement INNER JOIN user ON evenement.id_evenement=participation_eve.id_evenement;");
//"SELECT id_participation ,titre from evenement where`id_evenement` ='id_evenement', id_participant FROM participation_eve");
  //  "  SELECT participation_eve.id_participation, evenement.titre, participation_eve.id_participant FROM participation_eve INNER JOIN evenement ON evenement.id_evenement=participation_eve.id_evenement;");
return rs;
    }
    public ResultSet getReservationE() throws SQLException {
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery("SELECT p.id_participation , e.titre, u.name FROM participation_eve p INNER JOIN evenement e on e.id_evenement = p.id_evenement INNER JOIN user u on u.id = p.id_participant;");
         //     "SELECT participation_eve.id_participation ,participation_eve.id_evenement,participation_eve.id_participant, evenement.titre, user.firstname FROM participation_eve INNER JOIN evenement ON evenement.id_evenement=participation_eve.id_evenement INNER JOIN user ON evenement.id_evenement=participation_eve.id_evenement;");
//"SELECT id_participation ,titre from evenement where`id_evenement` ='id_evenement', id_participant FROM participation_eve");
  //  "  SELECT participation_eve.id_participation, evenement.titre, participation_eve.id_participant FROM participation_eve INNER JOIN evenement ON evenement.id_evenement=participation_eve.id_evenement;");
return rs;
    }
public ResultSet getReservationE3() throws SQLException{
        st = cnx.createStatement(); 
        ResultSet rs = st.executeQuery( 
            
      "SELECT COUNT(p.id_participation),e.titre  FROM participation_eve p   inner join evenement e  on p.id_evenement=e.id_evenement  GROUP BY p.id_evenement"); 
       

        
        return rs; 
    }
    public void delete(ReservationE t) throws SQLException {
        st = cnx.createStatement();
        String requeteDelete = "DELETE FROM `participation_eve` WHERE `id_participation` ='" + t.getId_participation()+ "';";
        st.executeUpdate(requeteDelete);
    }

    public void update(ReservationE t)throws SQLException {
        st = cnx.createStatement();
        String requeteUpdate = "UPDATE `evenement` SET `id_evenement` = '" + t.getId_evenement()+ "' ,`id_participant` = '" + t.getId_participant()+ "' WHERE `participation_eve`.`id_participation` = '" + t.getId_participation() + "' ;";
        st.executeUpdate(requeteUpdate);
    }
    public ResultSet TitreE() throws SQLException {
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery(
          //    "SELECT id_participation ,id_evenement,id_participant FROM participation_eve");
//"SELECT id_participation ,titre from evenement where`id_evenement` ='id_evenement', id_participant FROM participation_eve");
     "  SELECT  evenement.titre  FROM participation_eve INNER JOIN evenement ON evenement.id_evenement=participation_eve.id_evenement;");
return rs;
    }
    public ResultSet getReservationE2() throws SQLException{
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery(
               // "SELECT id_res_plat , id_client ,id_plat,quantity, date_reservation,etat FROM reservation_plats");
    
      //"SELECT  r.id_res_plat , r.id_client ,r.id_plat, p.nom,r.quantity, r.date_reservation FROM reservation_plats as r inner join plats as p on r.id_plat = p.id_plat ");
      "SELECT COUNT(id_evenement),DATE(date_debut) AS date FROM evenement GROUP BY date"); 
//"SELECT  m.id, m.nom , m.marque, m.quantite,m.image,m.id_categorie,m.id_etat,c.nom,e.type_etat FROM materiel as m left join categorie as c on m.id_categorie = c.id left join etat as e on m.id_etat=e.id";
        
        return rs;
    }

   
}
