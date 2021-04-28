/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.ReservationEService;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Statestique2Controller implements Initializable {

    @FXML
    private BarChart<String,Integer>  barChart;
    @FXML
    private Button btnLoad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         XYChart.Series<String,Integer> series = new XYChart.Series<>();
    
    ReservationEService ser = new ReservationEService();
    ResultSet rs = null;
        try {
            rs = ser.getReservationE3();
        } catch (SQLException ex) {
            Logger.getLogger(Statestique2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        try {
            while(rs.next()) {
                
                series.getData().add(new XYChart.Data<>(rs.getString(2),rs.getInt(1)));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Statestique2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    barChart.getData().add(series);
        // TODO
    }    

    @FXML
    private void loadChart(ActionEvent event) {
    }
    
}
