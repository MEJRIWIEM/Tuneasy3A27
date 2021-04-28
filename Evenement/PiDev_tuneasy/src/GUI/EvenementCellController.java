/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import Entities.Evenement;
import Entities.ReservationE;

import static GUI.EvenementController.ACCOUNT_SID;
import static GUI.EvenementController.AUTH_TOKEN;
import Service.ReservationEService;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class EvenementCellController extends ListCell<Evenement>{

    @FXML
    private GridPane gridpane;
    @FXML
    private ImageView img;
    @FXML
    private Label description;
    @FXML
    private Button btnPlat;
    @FXML
    private Label adresse;
    @FXML
    private Label titre;
    @FXML
    private Label dateD;
    @FXML
    private Label ville;
    @FXML
    private Label heure;
        private FXMLLoader mLLoader;

    /**
     * Initializes the controller class.
     * @param eve
     */
    @Override
    protected void updateItem(Evenement eve, boolean empty)  {
        super.updateItem(eve, empty);
        
       
        if(empty || eve == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("EvenementCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            titre.setText(eve.getTitre());
                        dateD.setText(eve.getDate_debut());

            ville.setText(eve.getVille());
            //ville.setText(restaurants.getVille());
            heure.setText(eve.getHeure());
            description.setText(eve.getDescription());
           /* Text text = new Text(restaurants.getDescription());
            description1.getChildren().add(text);*/
           //description1.setText(restaurants.getDescription());
            adresse.setText(eve.getAdresse());
          
          /*File filex = new File(restaurants.getPhoto());
          Image image = new Image(filex.toString());
          img.setImage(image);*/
            img.setImage(new Image( eve.getPhoto()));
            btnPlat.setOnAction(e->{
              
                
               ReservationE r = new ReservationE();
               r.setId_participant(55);
               r.setId_evenement(eve.getId_evenement());
            //   r.setDate_reservation(java.time.LocalDate.now());
          
        
          new ReservationEService().ajouter(r);
         Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        
        Message message = Message.creator(new PhoneNumber("+21626553014"), new PhoneNumber("+14158910369"), "Une nouvelle participation a été ajouter à l'événement : " +eve.getTitre()).create();
        System.out.println(message.getSid());
        
        
          JOptionPane.showMessageDialog(null, "Reservation effectué!");
        // Date date = date.setValue(LocalDate.parse(currentTime, DateTimeFormatter.ISO_DATE));
     //     qte.setText("");
            
            });
        setText(null);
            setGraphic(gridpane);}}
    @FXML
    private void pressPlat(ActionEvent event) {
        
    }
   /* public String eventDate(){
        LocalDate date1 =  date.getValue();
       
        int a =  date1.getDayOfMonth();
        
       int b = date1.getYear();
       int c = date1.getMonthValue();
        
             
      
           
        return b+"-"+c+"-"+a ;
    
    }*/
    
}
