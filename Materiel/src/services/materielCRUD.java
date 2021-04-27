/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.materiel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import tools.MyConnection;

/**
 *
 * @author asus
 */
public class materielCRUD {
    public void addMateriel(materiel m) {
        try {
            String requete = "INSERT INTO `materiel`(`nom_materiel`, `description_materiel`, `prix_materiel`, `photo_materiel`) VALUES (?,?,?,?)";

            PreparedStatement mat = MyConnection.getInstance().getCnx().prepareStatement(requete);
            
            mat.setString(1, m.getNom_materiel());
            mat.setString(2, m.getDescription_materiel());
            mat.setFloat(3, m.getPrix_materiel());
            mat.setString(4, m.getPhoto_materiel());
            mat.executeUpdate();

            System.out.println("Materiel ajoute avec succes!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean delete(int id) throws SQLException {
        int retour = JOptionPane.showConfirmDialog(null,
                "OK - Annuler",
                "Delete Materiel",
                JOptionPane.OK_CANCEL_OPTION);
        System.out.println("retour :" + retour);
        if (retour == 0) {
            PreparedStatement pre = MyConnection.getInstance().getCnx().prepareStatement("DELETE FROM materiel WHERE id ='" + id + "' ;");
            pre.executeUpdate();
        }
        return true;
    }

    public ObservableList<materiel> readAllmateriel() throws SQLException {
        ObservableList oblistdisc = FXCollections.observableArrayList();

        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery("select * from materiel ;");
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom_materiel = rs.getString("nom_materiel");
            String description_materiel = rs.getString("description_materiel");
            float prix_materiel = rs.getFloat("prix_materiel");
            String photo_materiel = rs.getString("photo_materiel");

            materiel m = new materiel(id, nom_materiel, description_materiel, prix_materiel, photo_materiel);
            oblistdisc.add(m);

        }
        return oblistdisc;
    }

    public boolean update(int id, String nom_materiel, String description_materiel, float prix_materiel, String photo_materiel) throws SQLException {
        PreparedStatement pre = MyConnection.getInstance().getCnx().prepareStatement(" UPDATE `materiel` SET `nom_materiel`='" + nom_materiel + "' ,`description_materiel`='" + description_materiel + "' ,`prix_materiel`='" + prix_materiel + "' ,`photo_materiel`='" + photo_materiel + "' WHERE id='" + id + "' ;");

        JOptionPane.showMessageDialog(null, "Materiel modifie avec succes");
        pre.executeUpdate();
        return true;
    }

    
}
