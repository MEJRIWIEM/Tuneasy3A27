/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.ReservationE;
import Service.ReservationEService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ReservationECellController  extends ListCell<ReservationE>{

    @FXML
    private GridPane gridpane;
    @FXML
    private Button btn_supp;
    @FXML
    private Button btn_mod;
   
    @FXML
    private Label id_client;
    private FXMLLoader mLLoader;
    @FXML
    private Label id_réservation;
    @FXML
    private Label id_evenement;


    /**
     * Initializes the controller class.
     * @param r
     * @param empty
     */
    @Override
    protected void updateItem(ReservationE r, boolean empty)  {
        super.updateItem(r, empty);
        
        

        if(empty || r == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("ReservationECell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            id_réservation.setText(String.valueOf(r.getId_participation()));
            id_client.setText(r.getTitre());
            id_evenement.setText(r.getName());

            
           
            
           btn_supp.setOnAction(e->{
               
        
        ReservationEService ser = new ReservationEService();
        
     try{   
      ser.delete(r);
     }catch(SQLException ex){JOptionPane.showMessageDialog(null, ex);};
   
         
         JOptionPane.showMessageDialog(null, "Suppression effectué!");
             

            
            });
           btn_mod.setOnAction(e->{
               
               ReservationE x = new ReservationE();
          x.setId_participation(r.getId_participation());
          x.setId_participant(r.getId_participant());
          x.setId_evenement(r.getId_evenement());
       
       
          
          ReservationEService ser = new ReservationEService();
          
                  
            try {
                ser.update(x);
            } catch (SQLException ex) {
                Logger.getLogger(ReservationECellController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
         
          
     
     
    
     
             });

            setText(null);
            setGraphic(gridpane);
            
            
            
      
        }
        // TODO
    }    
    
}
