/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacymanagementstudio;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static javax.lang.model.type.TypeKind.NULL;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class HistoryController implements Initializable {

    @FXML
    private JFXButton homeBtn;
    @FXML
    private JFXButton searchBtn;
    @FXML
    private JFXButton addNewBtn;
    @FXML
    private JFXButton sellProductBtn;
    @FXML
    private JFXButton historyBtn;
    @FXML
    private JFXButton editRecordBtn;
    @FXML
    private JFXButton profileBtn;
    @FXML
    private JFXButton logOutBtn;
    @FXML
    private JFXButton aboutBtn;
    @FXML
    private Label authenticationLabel;

    /**
     * Initializes the controller class.
     */
     String authentication,search_by,query;
     ResultSet resultSet;
     boolean flag,cartFlag;
      ConnectDatabase connect2=new ConnectDatabase();
      
      HashMap<String, String> searchKey = new HashMap<String, String>();
    @FXML
    private AnchorPane pane;
    @FXML
     public void homeButtonClicked(ActionEvent event) throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Admin_menu.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        Admin_menuController amc=loader.getController();
        amc.setAuthenticationLabel(authentication);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
    @FXML
     public void searchButtonClicked(ActionEvent event) throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("search.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        SearchController amc=loader.getController();
        amc.setAuthenticationLabel(authentication);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
    @FXML
     public void addNewButtonClicked(ActionEvent event) throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Admin_addNew.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        Admin_addNewController amc=loader.getController();
        amc.setAuthenticationLabel(authentication);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
    @FXML
     public void sellProductsButtonClicked(ActionEvent event) throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("sellMedicine.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        SellMedicineController amc=loader.getController();
        amc.setAuthenticationLabel(authentication);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
      public void historyButtonClicked(ActionEvent event) throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("history.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        HistoryController amc=loader.getController();
        amc.setAuthenticationLabel(authentication);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
    @FXML
       public void editRecordButtonClicked(ActionEvent event) throws IOException
    {
       FXMLLoader loader=new FXMLLoader(getClass().getResource("editRecords.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        EditRecordsController amc=loader.getController();
        amc.setAuthenticationLabel(authentication);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
    @FXML
        public void profileButtonClicked(ActionEvent event) throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("profile.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        ProfileController amc=loader.getController();
        amc.setAuthenticationLabel(authentication);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
    @FXML
         public void logoutButtonClicked(ActionEvent event) throws IOException
    {Stage window2=(Stage)((Node)event.getSource()).getScene().getWindow();
            window2.close();
        Parent adminmenuParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")); 
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
    @FXML
          public void aboutButtonClicked(ActionEvent event) throws IOException
    {
       FXMLLoader loader=new FXMLLoader(getClass().getResource("about.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        AboutController amc=loader.getController();
        amc.setAuthenticationLabel(authentication);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    } 

   
     public void setAuthenticationLabel(String strin){
        authentication=strin;
        if(authentication.contains("OWNER")){
            authenticationLabel.setText("OWNER");
        }
        else{
            authenticationLabel.setText("EMPLOYEE");
        }
         
   }   
}
