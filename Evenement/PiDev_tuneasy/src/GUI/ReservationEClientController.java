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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
public class ReservationEClientController implements Initializable {

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
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private ListView<ReservationE> eventList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
                
                eventList.setItems(loadE());
                eventList.setCellFactory(reservationsCellController -> new ReservationECellController());
                
            } catch (SQLException ex) {
                System.out.println(ex);
            } 
        // TODO
    }    
  public ObservableList<ReservationE> loadE() throws SQLException {
         
        ReservationEService ser = new ReservationEService();
        ResultSet rs = ser.getReservationE();
        
        ObservableList e = FXCollections.observableArrayList();
        
            while(rs.next()) {
            e.add(new ReservationE(
                  
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3)
                    
            )
                    
            );
                    
            
            
             
        }
        eventList.setItems(e);
        return e;
    } 

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        stage = (Stage) btnMenus.getScene().getWindow();
        stage.close();


        Parent root =FXMLLoader.load(getClass().getResource("EvenementClient.fxml"));
        Scene scene =new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Restaurants");
        stage.show();
    }

    @FXML
    private void pressButton(ActionEvent event) throws IOException {
          Stage stage = new Stage();
        stage = (Stage) btnOrders.getScene().getWindow();
        stage.close();


        Parent root =FXMLLoader.load(getClass().getResource("EvenementClient.fxml"));
        Scene scene =new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Restaurants");
        stage.show();
    }

    @FXML
    private void Reload(ActionEvent event) throws SQLException {
        loadE();
    }
    
}
