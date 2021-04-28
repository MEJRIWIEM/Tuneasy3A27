/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.pdfjet.*;
import Entities.Evenement;
import Service.EvenementService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class EvenementController implements Initializable {

    public static final String ACCOUNT_SID = "ACdc7ba4f48ffd28d5268527f966a34b50";
    public static final String AUTH_TOKEN = "555c07c750bdd6cae7f00857577e876a";
    File file;
    String Auxi;

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
    private Button btn_client;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TableView<Evenement> tvEvenements;
    @FXML
    private TableColumn<Evenement, String> adresse;
    @FXML
    private TableColumn<Evenement, String> ville;
    @FXML
    private TableColumn<Evenement, String> description;
    @FXML
    private TableColumn<Evenement, String> photo;

    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfDateD;
    @FXML
    private TextField tfDateF;
    @FXML
    private TextField tfHeure;
    @FXML
    private TextField tfVille;
    @FXML
    private TextField tfAdresseE;
    @FXML
    private TextField tfDescriptionE;
    @FXML
    private ImageView img;
    @FXML
    private Button image;
    @FXML
    private Button btnAjouterEve;
    @FXML
    private Button btn_gest_part;
    @FXML
    private TableColumn<?, ?> Titre;
    @FXML
    private TableColumn<?, ?> dateD;
    @FXML
    private TableColumn<?, ?> dateF;
    @FXML
    private TableColumn<?, ?> heure;
    @FXML
    private TableColumn<?, ?> id_resto;
    @FXML
    private Button btnStat;
    @FXML
    private Button btnStat1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {

            Titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            dateD.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
            dateF.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
            heure.setCellValueFactory(new PropertyValueFactory<>("heure"));
            ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
            adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            photo.setCellValueFactory(new PropertyValueFactory<>("photo"));

        } catch (NullPointerException e) {
            System.out.println("problem de connection = " + e.getMessage());
        } finally {
            try {
                tvEvenements.setItems(loadE());

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    public ObservableList<Evenement> loadE() throws SQLException {

        EvenementService ser = new EvenementService();
        ResultSet rs = ser.getEvenement();
        ObservableList e = FXCollections.observableArrayList();

        while (rs.next()) {
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
        tvEvenements.setItems(e);
        return e;
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }
private java.nio.file.Path to;
    private java.nio.file.Path from;
    private File selectedFile;
    
    @FXML
    private void imageAddE(ActionEvent event) throws IOException  {
          FileChooser fileChooserr = new FileChooser();
        fileChooserr.setTitle("Select Image");
        fileChooserr.setInitialDirectory(new File("C:"));
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooserr.getExtensionFilters().add(imageFilter);
        file = fileChooserr.showOpenDialog(img.getScene().getWindow());
        
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        Auxi = timeStamp+ file.getName();
        
        if (file != null) {
            from = Paths.get(file.toURI());
            
            to = Paths.get("C:/USERS/USER/Desktop/pidev/pidev/public/picture/" +Auxi);
            // Files.copy(from.toFile(), to.toFile()); //gives a 'cannot resolve method error 
            Files.copy(from, to);
        }
        
        Image image = new Image(file.toURI().toString());
        img.setImage(image);
    }

    @FXML
    public void AjouterE() throws SQLException {

        Evenement r = new Evenement();
        r.setId_organisateur(12345678);

        r.setTitre(tfTitre.getText());

        r.setDate_debut(tfDateD.getText());
        r.setDate_fin(tfDateF.getText());
        r.setHeure(tfHeure.getText());
        r.setVille(tfVille.getText());
        r.setAdresse(tfAdresseE.getText());
        r.setDescription(tfDescriptionE.getText());
        // r.setApprouver(1);
        r.setPhoto(file.toURI().toString());
        r.setNombre_max(10);
        r.setNombre_participants(10);
        r.setNombre_vus(0);

        new EvenementService().ajouter(r);
        /*tfnomR.setText("");
        tfadresseR.setText("");
        tfvilleR.setText("");
        tfdescriptionR.setText("");
        tftypeR.setText("");

        tfnumtelR.setText("");
        tfemailR.setText("");
        tfnoteR.setText("");*/
        loadE();

    }

    @FXML
    public void onEdit(MouseEvent event) throws SQLException {
        // check the table's selected item and get selected item
        Evenement even = new Evenement();
        if (tvEvenements.getSelectionModel().getSelectedItem() != null) {
            even = tvEvenements.getSelectionModel().getSelectedItem();
            tfTitre.setText(even.getTitre());
            tfDateD.setText(even.getDate_debut());
            tfDateF.setText(even.getDate_fin());
            tfHeure.setText(even.getHeure());
            tfVille.setText(even.getVille());
            tfAdresseE.setText(even.getAdresse());
            tfDescriptionE.setText(even.getDescription());
            File filex = new File(even.getPhoto());
            Image image = new Image("file:/C:/USERS/USER/Desktop/pidev/pidev/public/picture/" + filex.toString());

            img.setImage(image);
            //tfphotoR.setText(resto.getPhoto());
        }
    }

    @FXML
    public void ModifierE() throws SQLException {
        Evenement even = tvEvenements.getSelectionModel().getSelectedItem();

        Evenement r = new Evenement();
        r.setId_evenement(even.getId_evenement());
        r.setTitre(tfTitre.getText());
        r.setDate_debut(tfDateD.getText());
        r.setDate_fin(tfDateF.getText());
        r.setHeure(tfHeure.getText());
        r.setVille(tfVille.getText());

        r.setAdresse(tfAdresseE.getText());

        r.setDescription(tfDescriptionE.getText());

        EvenementService ser = new EvenementService();

        try {
            ser.update(r);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }

        loadE();

    }

    @FXML
    public void SupprimerE() throws SQLException {

        Evenement r = tvEvenements.getSelectionModel().getSelectedItem();

        EvenementService ser = new EvenementService();

        try {
            ser.delete(r);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        };

        JOptionPane.showMessageDialog(null, "Suppression effectué!");

        loadE();

    }

    @FXML
    public void pressE(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        stage = (Stage) btn_gest_part.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("ReservationE.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Gestion des reservations");
        stage.show();
    }

    @FXML
    private void gotoStat2(ActionEvent event) throws FileNotFoundException, Exception {
        /*   FileOutputStream fos= new FileOutputStream("Example1.pdf");
       PDF pdf = new PDF(fos);
     
       Page page = new Page(pdf , A4.PORTRAIT);
       page.drawLine(10, 10, 10, 10);
       pdf.close();
       fos.close();
        java.nio.file.Path file =  Paths.get("Example1.pdf");
        System.out.println("PDF WAS SAVED TO :"+ file.toAbsolutePath().toString());*/
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        //Message message = Message.creator(new PhoneNumber("+21652635795"), new PhoneNumber("+12245019503"), "Your Password is : "+passwordi).create();
        Message message = Message.creator(new PhoneNumber("+21626553014"), new PhoneNumber("+14158910369"), "test test ").create();
        System.out.println(message.getSid());
    }

    @FXML
    private void Boutton(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage = (Stage) btn_client.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("EvenementClient.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Évenements");
        stage.show();
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage = (Stage) btnStat1.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("Statestique2.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Statistique");
        stage.show();
    }

}
