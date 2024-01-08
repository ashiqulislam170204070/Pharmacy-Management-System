/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacymanagementstudio;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AddPreOrderController implements Initializable {

    @FXML
    private JFXButton backBtn;
    @FXML
    private JFXButton addBtn;

    /**
     * Initializes the controller class.
     */
    String authentication,query;
      ResultSet resultSet;
      ConnectDatabase connect2=new ConnectDatabase();
    @FXML
    private JFXTextField customerId;
    @FXML
    private JFXTextField medicineId;
    @FXML
    private JFXTextField quantity;
    @FXML
    private Text orderId;
    @FXML
    private JFXButton resetBtn;
    @FXML
    private AnchorPane pane;
    @FXML
    public void backButtonClicked(ActionEvent event) throws IOException
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
   public void resetButtonClicked(ActionEvent event)
    {
      orderId.setText("");
      customerId.setText("");
      quantity.setText("");
      medicineId.setText("");
    }
    public void addButtonClicked(ActionEvent event)
    {
      try{
          if(!(customerId.getText().isEmpty()||quantity.getText().isEmpty()||medicineId.getText().isEmpty()))
          { if(Integer.parseInt(quantity.getText())>0){
              
              try{
                  query="INSERT INTO PreOrder (Medicine_id, Quantity, Customer_id) VALUES ("+Integer.parseInt(medicineId.getText().substring(1, medicineId.getText().indexOf("]")))+","+Integer.parseInt(quantity.getText())+","+Integer.parseInt(customerId.getText().substring(1, customerId.getText().indexOf("]")))+")";
          
            connect2.connectDBUpdate(query);
                
            query="SELECT Order_id from PreOrder where Order_id=(SELECT max(Order_id) FROM PreOrder);";
            resultSet=connect2.connectDB(query);
            resultSet.next();
            orderId.setText(resultSet.getString("Order_id"));
                
            Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.INFORMATION;
             
            Alert alert=new Alert(type,"");
         
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("New Order has been added succefully");
            alert.getDialogPane().setHeaderText("Done");
            
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
          
        
        }catch (SQLException ex) {
            
            Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR; 
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setHeaderText("Failed");
             if(ex.getErrorCode()==2601)
                     alert.getDialogPane().setContentText("Data already exists");//Duplicate kew row
                else if(ex.getErrorCode()==547)
                    alert.getDialogPane().setContentText("Note: Medicine ID or Customer ID doesn't exist in database");
                else
                    alert.getDialogPane().setContentText("Error occured.Try again.");// default message
                
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
                //Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
   
                //Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);   
            }  
          }
          else{
           Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("Note: Quantity must be greater than zero");
            alert.getDialogPane().setHeaderText("Invalid Input");
            
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
          }
          }
          else{
          Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("Please fill all the fields first");
            alert.getDialogPane().setHeaderText("Failed");
            
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
          }
      }catch(Exception ex){}
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ///////////////////////////////////////////////////////////////////
        ArrayList<String> nameList=new ArrayList<String>();
        ArrayList<String> nameList2=new ArrayList<String>();
        try {
            query="SELECT Medicine_id,MedicineName FROM Medicine;";
            resultSet=connect2.connectDB(query);
            while(resultSet.next()){
            nameList.add("["+resultSet.getString("Medicine_id")+"] "+resultSet.getString("MedicineName"));
            }
            
            query="SELECT Customer_id,Name FROM Customer;";
            resultSet=connect2.connectDB(query);
            while(resultSet.next()){
            nameList2.add("["+resultSet.getString("Customer_id")+"] "+resultSet.getString("Name"));
            }  
                } catch (SQLException ex) {
            //Logger.getLogger(SellMedicineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AutoCompletionBinding<String> autocompletebinding = TextFields.bindAutoCompletion(medicineId,nameList);
        autocompletebinding.setPrefWidth(medicineId.getPrefWidth());
        autocompletebinding.setMaxWidth(medicineId.getMaxWidth());
        autocompletebinding.setVisibleRowCount(10);
        
        AutoCompletionBinding<String> autocompletebinding2 = TextFields.bindAutoCompletion(customerId,nameList2);
        autocompletebinding2.setPrefWidth(customerId.getPrefWidth());
        autocompletebinding2.setMaxWidth(customerId.getMaxWidth());
        autocompletebinding2.setVisibleRowCount(10);
  ///////////////////////////////////////////////////////////////////////////////////////////////////////
        quantity.addEventFilter(KeyEvent.KEY_TYPED , Number_Validation(9));
       
    }  

public EventHandler<KeyEvent> Number_Validation(final Integer max_Lengh) {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();                
            if (txt_TextField.getText().length() >= max_Lengh) {                    
                e.consume();
            }
            if(e.getCharacter().matches("[0-9]")){ 
                if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
                    e.consume();
                }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
                    e.consume(); 
                }
            }else{
                e.consume();
            }
        }
    };
}  
     public void setAuthenticationLabel(String strin){
       
         authentication=strin;
   }
}
