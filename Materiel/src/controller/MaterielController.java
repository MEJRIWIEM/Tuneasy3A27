/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.materiel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import services.materielCRUD;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class MaterielController implements Initializable {

    materiel m = new materiel();
    materielCRUD mc = new materielCRUD();
    
    
    @FXML
    private TextField tf_nom_materiel;
    @FXML
    private TextArea tf_description_materiel;
    @FXML
    private TextField tf_prix_materiel;
    @FXML
    private TextField tf_photo_materiel;
    @FXML
    private TableView<materiel> tableMateriel;
    @FXML
    private TableColumn<materiel, String> col_nom_materiel;
    @FXML
    private TableColumn<materiel, String> col_description_materiel;
    @FXML
    private TableColumn<materiel, Float> col_prix_materiel;
    @FXML
    private TableColumn<materiel, String> col_photo_materiel;
    @FXML
    private TextField tf_recherche_materiel;
    @FXML
    private ImageView exit_image_competition;
    @FXML
    private Button btn_ajouter_materiel;
    ObservableList<String> data = FXCollections.observableArrayList("nautique", "terrestre");
    @FXML
    private Button btn_delete_materiel;
    Stage stage;
    ObservableList<materiel> oblistmateriel = FXCollections.observableArrayList();
    @FXML
    private Button btn_update_materiel;
    @FXML
    private Label label_id_materiel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        label_id_materiel.setVisible(false); 
    }    

    private void clearAll() {
        tf_nom_materiel.setText("");
        tf_description_materiel.setText("");
        tf_prix_materiel.setText("");
        tf_photo_materiel.setText("");
    }

    private void initTable() {
        try {
            oblistmateriel = (ObservableList<materiel>) mc.readAllmateriel();
            col_nom_materiel.setCellValueFactory(new PropertyValueFactory<>("nom_materiel"));
            col_description_materiel.setCellValueFactory(new PropertyValueFactory<>("description_materiel"));
            col_prix_materiel.setCellValueFactory(new PropertyValueFactory<>("prix_materiel"));
            col_photo_materiel.setCellValueFactory(new PropertyValueFactory<>("photo_materiel"));
            tableMateriel.setItems(oblistmateriel);

            FilteredList<materiel> filteredData = new FilteredList<>(oblistmateriel, b -> true);

            tf_recherche_materiel.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(materiel -> {
                    // If filter text is empty, display all persons.

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (materiel.getNom_materiel().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches first name.
                    } else if (materiel.getDescription_materiel().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    } else {
                        return false; // Does not match.
                    }
                });
            });
            // 3. Wrap the FilteredList in a SortedList. 
            SortedList<materiel> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(tableMateriel.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            tableMateriel.setItems(sortedData);

        } catch (SQLException ex) {
            Logger.getLogger(MaterielController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @FXML
    private void ajouter(ActionEvent event) {
        if (tf_nom_materiel.getText().isEmpty() || tf_description_materiel.getText().isEmpty()
                || tf_prix_materiel.getText().isEmpty() || tf_photo_materiel.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");

        } else if (!tf_prix_materiel.getText().matches("^[0-9]+$")) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir un prix valable");
        } else {

            materielCRUD mc = new materielCRUD();
            materiel m = new materiel();
            m.setNom_materiel(tf_nom_materiel.getText());
            m.setDescription_materiel(tf_description_materiel.getText());
            m.setPrix_materiel(Float.valueOf(tf_prix_materiel.getText()));
            m.setPhoto_materiel(tf_photo_materiel.getText());

            mc.addMateriel(m);
            clearAll();
            initTable();

            Image img = new Image("/img/ok.png");
            Notifications notifAdd = Notifications.create()
                    .title("ajout avec succes")
                    .text("enregistre avec succes")
                    .graphic(new ImageView(img))
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);
            notifAdd.show();
        }
    }
    
    @FXML
    private void delete(ActionEvent event)  throws SQLException{
        materiel mat= (materiel) tableMateriel.getSelectionModel().getSelectedItem();
        if (!mat.equals(null)) {
            mc.delete(mat.getId());
            initTable();
            clearAll();
        }
    }
    
    @FXML
    private void handleAction(MouseEvent event) throws SQLException {
        materiel m = (materiel) tableMateriel.getSelectionModel().getSelectedItem();
        tf_nom_materiel.setText(m.getNom_materiel());
        tf_description_materiel.setText(m.getDescription_materiel());
        tf_prix_materiel.setText(String.valueOf(m.getPrix_materiel()));
        tf_photo_materiel.setText(m.getPhoto_materiel());
        label_id_materiel.setText(String.valueOf(m.getId()));
    }

    @FXML
    private void exit(MouseEvent event) {
        stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        materiel m = tableMateriel.getSelectionModel().getSelectedItem();
        if (m == null) {
            JOptionPane.showMessageDialog(null, "Rien n est selectionne !");
        } else {
           if (tf_nom_materiel.getText().isEmpty() || tf_description_materiel.getText().isEmpty()
                || tf_prix_materiel.getText().isEmpty() || tf_photo_materiel.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");

        } else if (!tf_prix_materiel.getText().matches("^[0-9]+$")) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir un prix valable");
        } else {
                //int id, String nom, String description,int nbplace, String category, String adresse, String image
                mc.update(m.getId(), tf_nom_materiel.getText(),tf_description_materiel.getText(),Float.valueOf(tf_prix_materiel.getText()),tf_photo_materiel.getText());
                initTable();
                clearAll();
            }
        }
    }
    
}
