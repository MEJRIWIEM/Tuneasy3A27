/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Evenement;
import Service.EvenementService;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class EvenementClientController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btn_admin;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button btn_gest_res;
    @FXML
    private ListView<Evenement> eventList;

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODOtry {

            eventList.setItems(loadE());
        } catch (SQLException ex) {
            Logger.getLogger(EvenementClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
                eventList.setCellFactory(evenementsCellController -> new EvenementCellController());
                
            } 
            

    private ObservableList<Evenement> loadE() throws SQLException {
EvenementService ser = new EvenementService();
        ResultSet rs = ser.getEvenement();
        ObservableList e = FXCollections.observableArrayList();
        
            while(rs.next()) {
            e.add(new Evenement(
                  
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getBoolean(11),
                    rs.getInt(12),
                    rs.getInt(13),
                    rs.getInt(14)
            ));
                    
            
           
            
             
        }
        eventList.setItems(e);
        return e;    }
      

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        stage = (Stage) btn_admin.getScene().getWindow();
        stage.close();


        Parent root =FXMLLoader.load(getClass().getResource("EvenementClient.fxml"));
        Scene scene =new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Gestion des événements");
        stage.show();
    }

    @FXML
    private void adminpress(ActionEvent event) throws IOException {
           Stage stage = new Stage();
        stage = (Stage) btn_admin.getScene().getWindow();
        stage.close();


        Parent root =FXMLLoader.load(getClass().getResource("Evenement.fxml"));
        Scene scene =new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Gestion des événements");
        stage.show();
    }

    @FXML
    private void press(ActionEvent event) throws IOException {
          Stage stage = new Stage();
        stage = (Stage) btn_gest_res.getScene().getWindow();
        stage.close();


        Parent root =FXMLLoader.load(getClass().getResource("ReservationEClient.fxml"));
        Scene scene =new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Mes Reservations");
        stage.show();
    }
    
}
