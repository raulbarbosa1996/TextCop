/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textcop;

import static com.sun.org.apache.xml.internal.serialize.LineSeparator.Web;
import hultig.io.FileIN;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;

/**
 * FXML Controller class
 *
 * @author raulb_000
 */
public class PageWebController implements Initializable {

     List<String> teste=new ArrayList();
     
      
    
    String htLink="http://";
    String addressLink;
    @FXML
    WebView web;
    @FXML 
    WebEngine engine;
    @FXML
    TextField addressBar;
    /**
     * Initializes the controller class.
     */
    
    public void go(ActionEvent actionEvent){
        addressLink=addressBar.getText().toString();
        engine.load(htLink+addressLink);
    }
    
    
  
    
    
    @FXML
    private void butBackAction(ActionEvent event) {
        Platform.runLater(() -> {
            engine.executeScript("history.back()");
        });
    }
    
    
    
    //teste
    @FXML
    private void butTextAction(ActionEvent event) {
        List<String> list= extractTextFromPage();
        
        
        FileIN fpalavras=  new FileIN("C:\\Users\\raulb_000\\Desktop\\3 ano\\IHC\\TextCop\\src\\pesquisa.txt","ISO-8859-1");
           
        teste= new ArrayList<String >(Arrays.asList(fpalavras.readAll()));
        
        try {
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLText.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage(StageStyle.UTILITY);
            stage.setTitle("Texto");
            stage.setScene(new Scene(root));
            stage.show();
            
            FXMLTextController controller = fxmlLoader.getController();
            //controller.oi(teste);
            controller.setText(list);
            
            controller.search(list, teste);
           
            
            
            
            
        } catch (IOException e) {
            System.err.println(e);
        }
       
    }
    
    
    public List<String> extractTextFromPage() {
        Document d= web.getEngine().getDocument();
        W3CDom   dom= new W3CDom();
        org.jsoup.nodes.Document doc= Jsoup.parse(dom.asString(d));
        Elements textElements= doc.select("p,li,h1,h2,h3,h4");
        ArrayList<String> list= new ArrayList<>();
        for (Element e : textElements) {
            if ( e.hasText() ) {
                String t= e.text().trim();
                if ( "h1|h2|h3|h4".contains(e.tag().toString())) {
                    list.add("\n"+t+"\n");
                }
                else {
                    if (".;!?".indexOf(t.charAt(t.length() - 1)) >= 0) {
                        list.add(t);
                    }
                }
            }
        }
        
        return list;
    }   
    
    


    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        engine=web.getEngine();
        engine.load("https://www.google.com");
        
        
    }    
    
}
