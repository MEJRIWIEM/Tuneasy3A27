/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.ReservationE;
import Service.EvenementService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import Service.ReservationEService;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ReservationEController implements Initializable {

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
    private Button btn_gest_eve;
    @FXML
    private TableView<ReservationE> tvPartiE;
    @FXML
    private TableColumn<ReservationE, Integer> col_idR;
    @FXML
    private TableColumn<ReservationE, Integer> col_idE;
    @FXML
    private TableColumn<ReservationE, Integer> col_idO;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            col_idR.setCellValueFactory(new PropertyValueFactory<>("id_participation"));
            col_idE.setCellValueFactory(new PropertyValueFactory<>("titre"));
            col_idO.setCellValueFactory(new PropertyValueFactory<>("name"));

        } catch (NullPointerException e) {
            System.out.println("problem de connection = " + e.getMessage());
        } finally {
            try {
                tvPartiE.setItems(loadE());

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        // TODO
    }

    public ObservableList<ReservationE> loadE() throws SQLException {

        ReservationEService ser = new ReservationEService();
        ResultSet rs = ser.getReservationE();
        ObservableList e = FXCollections.observableArrayList();

        while (rs.next()) {
            e.add(new ReservationE(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3)
            )
                    
            );

        }
        
        tvPartiE.setItems(e);
        return e;
    }

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage = (Stage) btn_gest_eve.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("Evenement.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Gestion des Événements");
        stage.show();

    }


    @FXML
    private void SupprimerR(ActionEvent event) throws SQLException {
        ReservationE p = tvPartiE.getSelectionModel().getSelectedItem();

        ReservationEService ser = new ReservationEService();

        try {
            ser.delete(p);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        };

        JOptionPane.showMessageDialog(null, "Suppression effectué!");

        loadE();
    }


    @FXML
    private void pressE(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage = (Stage) btn_gest_eve.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("Evenement.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Gestion des Événements");
        stage.show();
    }

    @FXML
    private void aff(ActionEvent event) throws  Exception {

        ReservationE r = tvPartiE.getSelectionModel().getSelectedItem();
        String idc = r.getName();
        String ide = r.getTitre();
        ReservationEService ser = new ReservationEService();
        ResultSet rs = ser.getUserEmail(idc);
        String email = "";
        System.out.println("email: " + rs);
        while (rs.next()) {
            email = rs.getString(1);

            System.out.println("email: " + email);

        }
      
        sendEmailE(email,idc, ide);

        loadE();
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String nom, String titre) throws AddressException, MessagingException {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myAccountEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
        message.setSubject("Evenement [ " + titre + " ]  ");
        String htmlCode = "<h1><h3>Participation</h3>\n"
                + "\n"
                + "<h4>Bonjour " + nom + ",</h4>\n"
                + "\n"
                + "<h4>Ceci est un mail pour vous rappeler que vous avez participé à l'événement " + titre+ ",</h4>\n"
                + "<h4>On vous remercie pour votre confiance, Tuneasy.</h4>";
        message.setContent(htmlCode, "text/html");

        return message;

    }

    public static void sendEmailE(String recepient, String nom, String titre) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

//Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "tuneasy.restaurants@gmail.com";
        //Your gmail password
        String password = "Tun.rest.3006";

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }

        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient, nom,titre);

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    @FXML
    private void PDF(ActionEvent event) {
        try {
             EvenementService ser = new EvenementService();
      
            String file_name = "C:\\Users\\USER\\Desktop\\evenement.pdf";
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();

            PdfWriter.getInstance(document, new FileOutputStream(file_name));

            document.open();
            Paragraph p = new Paragraph("  ");
            Image img = Image.getInstance("C:\\Users\\USER\\Downloads\\CRUD1\\PiDev_tuneasy\\src\\images\\logo.png");
            

            img.scaleAbsolute(100, 100);
            img.setAbsolutePosition(450, 630);
            document.add(img);
//            
            document.add(p);
            document.add(p);
            document.add(p);
            
            document.add(p);
            document.add(p);
            document.add(p);

           
            PreparedStatement psF = null;
            ResultSet rsF = null;

            PreparedStatement psP = null;
            ResultSet rsP = null;

            PreparedStatement psC = null;
            ResultSet rsC = null;

            

            
            rsF = ser.getEvenement();
            int i = 1;
            int j = 1;
            int k = 1;
           
            while (rsF.next()) {
                Paragraph p2 = new Paragraph("Événement n°" + i);
                document.add(p2);

                Paragraph para = new Paragraph("Le Titre : " + rsF.getString("titre") + " \nLa Description   :" + rsF.getString("description")+ " \n  ______________________________ \n   ");
                document.add(para);
                int idForum = rsF.getInt("id_evenement"); 
                document.add(new Paragraph("  "));

               
                j = 1;

                i++;
               
            }

           

//          
            document.close();
            System.out.println("PDF created");
            
            Desktop.getDesktop().open(new File(file_name));
        } catch (Exception e) {
            System.err.println(e);
        }
        
    }

    }


