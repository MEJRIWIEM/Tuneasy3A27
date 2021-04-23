
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.compcrud;
import java.sql.Statement;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Maya
 */
public class MainController implements Initializable {
    
    @FXML
    private TextField tfid;
     @FXML
    private TextField tfnom;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfcategorie;
    @FXML
    private TextField tfnombredeplace;
    @FXML
    private TextField tfimage;
     @FXML
    private TextField tfadresse;
      @FXML
    private TextField tfdate;
    @FXML
    private TableView<competition> tvcompetition;
    @FXML
    private TableColumn<competition, Integer> colid;
    @FXML
    private TableColumn<competition, String> colnom;
    @FXML
    private TableColumn<competition, String> coldescription;
    @FXML
    private TableColumn<competition, String> colcategorie;
    @FXML
    private TableColumn<competition, Integer> colnombredeplace;
     @FXML
    private TableColumn<competition, String> colimage;
      @FXML
    private TableColumn<competition, String> coladresse;
       @FXML
    private TableColumn<competition, String> coldate;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Pagination pagination;
    @FXML
    private TextField filterField;
    @FXML
    private Button printbtn;
    
    private final ObservableList<competition> data=FXCollections.observableArrayList();
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
         if(event.getSource() == btnajouter){
            insertRecord();
        }else if (event.getSource() == btnmodifier){
            updateRecord();
        }else if(event.getSource() == btnsupprimer){
            deleteButton();
        }else if (event.getSource() == printbtn)
             printbtnActionPerformed();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    showComp();
    }    
    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tuneasy?serverTimezone=UTC", "root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
     public ObservableList<competition> getCompList(){
        ObservableList<competition> compList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM competition";
        Statement st;
        ResultSet rs;
        
        try{
            st = (Statement) conn.createStatement();
            rs = st.executeQuery(query);
            competition competitions;
            while(rs.next()){
                competitions = new competition(rs.getInt("id"), rs.getString("nom"), rs.getString("description"),rs.getString("categorie"),
                        rs.getInt("nombredeplace"),rs.getString("image"),rs.getString("adresse"),rs.getInt("date"));
                compList.add(competitions);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return compList;
    }
    
    public void showComp(){
        ObservableList<competition> list = getCompList();
        
        colid.setCellValueFactory(new PropertyValueFactory<competition, Integer>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<competition, String>("nom"));
        coldescription.setCellValueFactory(new PropertyValueFactory<competition, String>("description"));
        colcategorie.setCellValueFactory(new PropertyValueFactory<competition, String>("categorie"));
        colnombredeplace.setCellValueFactory(new PropertyValueFactory<competition, Integer>("nombredeplace"));
        colimage.setCellValueFactory(new PropertyValueFactory<competition, String>("image"));
        coladresse.setCellValueFactory(new PropertyValueFactory<competition, String>("adresse"));
        coldate.setCellValueFactory(new PropertyValueFactory<competition, String>("date"));
        tvcompetition.setItems(list);
        int page=1 ;
        if (getCompList().size()/fileForPagination()==0)
        {
            page=getCompList().size()/fileForPagination();
            
        }
        else if (getCompList().size()>fileForPagination()){
                        page=getCompList().size()/fileForPagination()+ 1;

        }
       pagination.setPageCount(page);
       pagination.setCurrentPageIndex(2);
       pagination.setMaxPageIndicatorCount(1);
       pagination.setPageFactory(this::CreatePagination);
       
       
       FilteredList<competition> filteredData = new FilteredList<>(data,b->true);
       filterField.setOnKeyReleased(b->{
       filterField.textProperty().addListener((observable,oldValue,newValue)->{
           filteredData.setPredicate((Predicate <? super competition>) competition->{
               if (newValue == null || newValue.isEmpty()){
                   return true ;
               }
               String lowerCaseFilter = newValue.toLowerCase();
               if(competition.getNom().toLowerCase().contains(lowerCaseFilter) ){
                   return true ;
               }
               else if (competition.getCategorie().toLowerCase().contains(lowerCaseFilter)){
                   return true ;
               }
               return false;
           });
           });
       });
       
        SortedList<competition> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvcompetition.comparatorProperty());
        tvcompetition.setItems(sortedData);
       
    }
    private void insertRecord(){
        String query = "INSERT INTO competition VALUES (" + tfid.getText() + ",'" + tfnom.getText() + "','" + tfdescription.getText() + "','"
                + tfcategorie.getText() + "'," + tfnombredeplace.getText() + ",'"+tfimage.getText()+ "','"+tfadresse.getText()+"',"+tfdate.getText()+")";
        executeQuery(query);
         showComp();
    }
    private void updateRecord(){
        String query = "UPDATE  competition SET nom  = '" + tfnom.getText() + "', description = '" + tfdescription.getText() + "', categorie = '" +
                tfcategorie.getText() + "', nombredeplace = " + tfnombredeplace.getText() + ", image = '" + tfimage.getText() + "', adresse = '" + tfadresse.getText() + "', date = " 
                + tfdate.getText() +" WHERE id = " + tfid.getText() + "";
      
        executeQuery(query);
        showComp();
    }
    private void deleteButton(){
        String query = "DELETE FROM competition WHERE id =" + tfid.getText() + "";
        

        executeQuery(query);
        showComp();
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = (Statement) conn.createStatement();
            st.executeUpdate(query);
        }catch(SQLException ex){
        }
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
         competition Competition = tvcompetition.getSelectionModel().getSelectedItem();
        tfid.setText("" + Competition.getId());
        tfnom.setText(Competition.getNom());
        tfdescription.setText(Competition.getDescription());

        tfcategorie.setText(Competition.getCategorie());
        tfnombredeplace.setText(""+Competition.getNombredeplace());
        tfimage.setText(Competition.getImage());
        tfadresse.setText(Competition.getAdresse());
        tfdate.setText(""+Competition.getDate());


       
    }
    private void printbtnActionPerformed()
    {
       
        
        String titre ="liste competitions";
        Divers.imprimerJtable(tvcompetition, titre);
        
        
    }
    
    
    
    public int fileForPagination(){
    return 2 ;
}
    
    private Node CreatePagination (int pageIndex)
    {
    
    int fromIndex = pageIndex * fileForPagination();
    
    int toIndex = Math.min(fromIndex+fileForPagination(),getCompList().size());
    
    tvcompetition.setItems(getCompList());
    
    return  new BorderPane(tvcompetition);
}

}