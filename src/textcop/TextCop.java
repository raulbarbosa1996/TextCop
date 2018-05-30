/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textcop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author raulb_000
 */
public class TextCop extends Application {
    private static Stage primaryStage;
    
    private void setPrimaryStage(Stage stage){
        TextCop.primaryStage=stage;
    }
    
    static public Stage getPrimaryStage(){
        return TextCop.primaryStage;
    }
    
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        setPrimaryStage(stage);
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
       
        Image applicationIcon = new Image(getClass().getResourceAsStream("Cop_Cap.png"));
        stage.getIcons().add(applicationIcon);
        stage.setScene(scene);
        stage.show();
        stage.resizableProperty().setValue(Boolean.FALSE);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
