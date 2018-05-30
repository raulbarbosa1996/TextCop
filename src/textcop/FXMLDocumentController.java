/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package textcop;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Popup;
import javafx.stage.PopupBuilder;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author raulb_000
 */
public class FXMLDocumentController implements Initializable {
    
   @FXML 
   private ImageView img,img1;
   

   
 
   
    
    @FXML
    private void handleButtonActionFile(ActionEvent event) {
        
        try{    
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChoseFile.fxml"));
    Parent root1 = (Parent) fxmlLoader.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(root1)); 
    stage.show();
    
    
    
    
    
    }
    catch(Exception e){
        System.out.println("Cant load new window");
    }  
        
    }
    
    @FXML
    private void About(ActionEvent event) {
       
       
        
    }
    
    
    @FXML
    private void close(ActionEvent event) {
        Alert alert1 = new Alert(AlertType.CONFIRMATION);
            String s = "Deseja sair da aplicação?";
            alert1.setContentText(s);

            Optional<ButtonType> result = alert1.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
        Platform.exit();
        }
    }
    
    @FXML
    private void handleButtonActionWeb(ActionEvent event) {
        
        
        
    try{    
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PageWeb.fxml"));
    Parent root1 = (Parent) fxmlLoader.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(root1)); 
    stage.show();
    }
    catch(Exception e){
        System.out.println("Cant load new window");
    }  
        
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        File file = new File("src/Imagem2.png");
        Image image = new Image(file.toURI().toString());
        img.setImage(image);
        
        File file1 = new File("src/search.jpg");
        Image image1 = new Image(file1.toURI().toString());
        img1.setImage(image1);
    }    
    
}
