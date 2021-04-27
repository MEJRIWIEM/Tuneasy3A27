/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.materiel;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import services.materielCRUD;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class BackmaterielController implements Initializable {
    
    materiel m = new materiel();
    materielCRUD mc = new materielCRUD();


    @FXML
    private TableColumn<materiel, String> col_nom_materiel;
    @FXML
    private TableColumn<materiel, String> col_description_materiel;
    ObservableList<materiel> oblistmateriel = FXCollections.observableArrayList();
    private Connection con;
    @FXML
    private Button PDF_materiel;
    @FXML
    private TableView<materiel> tableMateriel;
    @FXML
    private TableColumn<materiel, Float> col_prix_materiel;
    @FXML
    private TableColumn<materiel, String> col_photo_materiel;
    
    public BackmaterielController() 
    {
    con = MyConnection.getInstance().getCnx();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
    }    

    @FXML
    private void handleAction(MouseEvent event) throws SQLException {
        materiel m = tableMateriel.getSelectionModel().getSelectedItem();

        //initTableP(F.getId());
        //loadData2(m.getId());
       // loadData1(c.getId());
    }
    
    private void initTable() {
        try {
            oblistmateriel = (ObservableList<materiel>) mc.readAllmateriel();
            col_nom_materiel.setCellValueFactory(new PropertyValueFactory<>("nom_materiel"));
            col_description_materiel.setCellValueFactory(new PropertyValueFactory<>("description_materiel"));
            col_prix_materiel.setCellValueFactory(new PropertyValueFactory<>("prix_materiel"));
            col_photo_materiel.setCellValueFactory(new PropertyValueFactory<>("photo_materiel"));
            tableMateriel.setItems(oblistmateriel);

        } catch (SQLException ex) {
            Logger.getLogger(MaterielController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void PDF(ActionEvent event) {
        try {
            materielCRUD es = new materielCRUD();
            materiel panier = new materiel();
            //PanierService panierService = new PanierService();
            // panier = panierService.getCurrentPanierByUserID(Main.user_id);

            String file_name = "C:\\Users\\asus\\Documents\\NetBeansProjects\\projet\\Materiel.pdf";
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(file_name));

            document.open();
            Paragraph p = new Paragraph("  ");
            

            
//            
            document.add(p);
            document.add(p);
            document.add(p);
            
            document.add(p);
            document.add(p);
            document.add(p);

            Connection connexion = MyConnection.getInstance().getCnx();
            PreparedStatement psF = null;
            ResultSet rsF = null;

            PreparedStatement psP = null;
            ResultSet rsP = null;

            PreparedStatement psC = null;
            ResultSet rsC = null;

            String queryF = "select * from materiel";

            psF = (PreparedStatement) connexion.prepareStatement(queryF);
            rsF = psF.executeQuery();
            int i = 1;
            int j = 1;
            int k = 1;
            materielCRUD ser = new materielCRUD();
            while (rsF.next()) {
                Paragraph p2 = new Paragraph("Materiel n°" + i);
                document.add(p2);

                Paragraph para = new Paragraph("Materiel nom : " + rsF.getString("nom_materiel") + " \nDescription   :" + rsF.getString("description_materiel") + " \nPrix   :" + rsF.getFloat("prix_materiel") +" " + "TND" );
                document.add(para);
                int idForum = rsF.getInt("id");
                document.add(new Paragraph("  "));

                /*String queryP = "select * from post where frm_id= '" + idForum + "';";
                psP = (PreparedStatement) connexion.prepareStatement(queryP);
                rsP = psP.executeQuery();
                while (rsP.next()) {
                    document.add(new Paragraph("                  "));
                    Paragraph p2P = new Paragraph("          Post n°" + j);
                    document.add(p2P);

                    Paragraph paraP = new Paragraph("          Title Post : " + rsP.getString("title") + "\n          Description   :" + rsP.getString("description")+"\n          Views : "+rsP.getInt("views")+"\n          Number of comments : "+rsP.getInt("noc")+"\n          Date : "+rsP.getDate("creat_at"));
                    document.add(paraP);
                    j++;
                    
                    document.add(new Paragraph("  "));
                    int idPost = rsP.getInt("id");
                    String queryC = "select * from comment where pst_id = '" + idPost + "';";
                    psC = (PreparedStatement) connexion.prepareStatement(queryC);
                    rsC = psC.executeQuery();
                    while (rsC.next()) {
                        Paragraph p2C = new Paragraph("                    Comment n°" + k);
                        document.add(p2C);

                        Paragraph paraC = new Paragraph("                    Content Comment : " + rsC.getString("content")+"\n                    Date :"+rsC.getDate("creat_at")+"\n                    Rating for the Post :"+rsC.getInt("rating"));
                        document.add(paraC);
                        
                        document.add(new Paragraph("  "));
                        k++;

                    }
                    k = 1;
                */
                j = 1;

                i++;
            }

            Paragraph parrr = new Paragraph("________________________________");
            parrr.setAlignment(Element.ALIGN_CENTER);
            document.add(parrr);

//          
            document.close();
            System.out.println("PDF cree avec succes");
            
            Desktop.getDesktop().open(new File(file_name));
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
}
