/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textcop;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import hultig.io.FileIN;
import hultig.sumo.Sentence;
import hultig.sumo.Text;
import hultig.sumo.Word;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author raulb_000
 */
public class FXMLTextController implements Initializable {

    /**
     * Initializes the controller class.
     */
  

    List<String> palavrasenc = new ArrayList();
    List<String> palavrasencsr = new ArrayList();
    List<Integer> numero = new ArrayList<Integer>();
    
    
    String[] linhas;
    
  
    
    @FXML
    private TextArea texto,resultado1,resultado;
    @FXML
    private Label label;
    
   
    
    @FXML
    private WebView webView;
    
    
    WebEngine webEngine;
    FileIN fpalavras = new FileIN("C:\\Users\\raulb_000\\Desktop\\3 ano\\IHC\\TextCop\\src\\pesquisa.txt", "ISO-8859-1");

    List <String> teste  = new ArrayList<String>(Arrays.asList(fpalavras.readAll()));
    List<String> teste1= new ArrayList();
    List<String> teste2= new ArrayList();
    
    
    
    
    public void setText(List<String> t) {
        texto.clear();
        for (String s : t) {
            texto.appendText(s + ' ');
           
        }
    }
    
    public void FindAndColor() {
        webEngine=webView.getEngine();
        String st = texto.getText().toLowerCase();
       
       StringBuilder sb= new StringBuilder(
                "<!DOCTYPE html>"
                +"<head>"
                + "<style>"
                +"yellow{"  
                + "color: black;"
                + "background-color:#ff8080;"
                + "}"
                + "green{"
                + "color: black;"
                + "background-color:green;"
                + "}"
                + "blue{"
                + "color: black;"
                + "background-color:#4682B4;"
                + "}"        
                + "</style>"
                + "</head>"
                + "<body>"
                + "<p>");
        hultig.sumo.Text x = new Text(st);
        StringBuilder rs = new StringBuilder();
        for (Sentence s : x) {
            for (Word w : s) {
                int i;
                for (i = 0; i < teste.size(); i++) {
                    if (w.equals(teste.get(i))) {
                        Word wx;
                        if (teste1.contains(w.toString())) {
                            wx = new Word("<yellow>" + w + "</yellow>");
                        } else if (teste2.contains(w.toString())) {
                            wx = new Word("<blue>" + w + "</blue>");
                        } else {
                            wx = new Word("<green>" + w + "</green>");
                        }
                        sb.append(" " + wx.toString());
                        break;
                    }
                }
                if (i == teste.size()) {
                    sb.append(" " + w);
                }

            }
        }
        sb.append(" </p>"
                + "</body>"
                + "</html>");

        webEngine.loadContent(sb.toString());

    }
    

    
    /*
    public void oi(List<String> teste){
         for(int i=0;i<teste.size();i++){
            resultado.setText(teste.get(i)+ "\n");
             System.out.println(teste.get(i));
        }    
    }*/
    

    public void search(List<String> t, List<String> proibidas) {
        //palavras.add(palavra1);
        //System.out.println(palavras.size());
        StringBuilder sb = new StringBuilder();
        for (String s : t) {
            sb.append(s);
        }
        

        Text te = new Text(sb.toString().toLowerCase());
        
        FileIN ffrases=  new FileIN("C:\\Users\\raulb_000\\Desktop\\3 ano\\IHC\\TextCop\\src\\pesquisafrases.txt","ISO-8859-1");
        linhas = ffrases.readAll();

        StringBuilder sb1 = new StringBuilder();
        for (String linha : linhas) {
            sb1.append(linha).append(' ');
        }
        
        Text teste = new Text(sb1.toString().toLowerCase());
        //
        
        searchSentence(te,teste,"");
        //searchWithHash(te,proibidas);
       
      
        for (Sentence s : te) {
            for (Word w : s) {
                for (int i = 0; i < proibidas.size(); i++) {
                    if (w.equals(proibidas.get(i))) {                        
                        //encontrou uma palavra igual as palavras proibidas
                        palavrasenc.add(proibidas.get(i).toString());
                        if(!palavrasencsr.contains(proibidas.get(i))){
                            
                            palavrasencsr.add(proibidas.get(i));
                        }
                       
                    }
                }

               
            }
        }
        
        
            for(int j=0;j<palavrasencsr.size();j++){
                int conta=0;
                for(int i=0;i <palavrasenc.size();i++){
                    if(palavrasencsr.get(j).equals(palavrasenc.get(i)))
                    {
                    conta=conta+1;
                    
                    }
                }
                 numero.add(j,conta);
                
            }
        
        resultado.setText("");
        int contap = palavrasencsr.size();
        if (contap == 1) {
            resultado.setText("\n" + "Foram encontradas: 1 % de palavras proibidas." + "\n");
        } else {
            if (contap != 0 && proibidas.size() != 0) {
                double c = 0.0;
                c = (float) contap / proibidas.size() * 100;
                DecimalFormat df = new DecimalFormat("0.0000"
                        + ""
                        + "+"
                        + "+"
                        + "         ");
                String f = df.format(c);
                String[] res = f.split(",");
                //double fim=Double.parseDouble(f);
                //System.out.println(f);
                //System.out.println(res[0]);
                resultado.setText("\n" + "Foram encontradas: " + res[0] + " % de palavras proibidas :" + "\n");
            }
        }
        if(palavrasencsr.isEmpty()){
            resultado.setText("Nenhuma correspondecia");
        }
        
        for(int i=0;i<palavrasencsr.size();i++){
           
            resultado.setText(resultado.getText()+"\n"+palavrasencsr.get(i)+": "+numero.get(i)+"\n");
            
        }    
        
 
        FindAndColor();
        palavrasenc.clear();
        palavrasencsr.clear();
        numero.clear();
        te.clear();
    }
    
    
    
    
    //procurar por frases
    public void searchSentence(Text ta,Text tb,String id){
        Sentence[] sa=ta.getSentences();
        Sentence[] sb=tb.getSentences();
        String s = "";
        String x = "";
        int flag=0;
        int i=0;
        for(Sentence sal : sa){
            TreeSet<String> intersecVaVb= new TreeSet();
            intersecVaVb.addAll(Arrays.asList(sal.getWords()));
            i++;
            int j =0;
             
            sal.toLowerCase();
            sal.setMetric("NGRAM");
            flag=0;
            x="";
            for (Sentence sb1 : sb) {
                j++;

                intersecVaVb.retainAll(Arrays.asList(sb1.getWords()));

                int a = sal.getWords().length;
                int b = sb1.getWords().length;
                int aeb =intersecVaVb.size();

                double m = ((double)(2*aeb))/((double)(a+b));
                
                sb1.toLowerCase();
                sal.metric=Sentence.Metric.NGRAM;
                double sim = sal.similarity(sb1);
                /* descomentar para ver todos as sentences a serem verificadas
                if(m>=0.001){
                    x = x+"->"+sb1.toString()+" ("+id+")\n Vocabulary Intersection: "+(int)(m*100)+"\n Similiarity: " +(int)(sim*100)+"%\n";
                    flag=1;
                }*/
                if(sim>0.05||m*100>20){
                    //x = x+"->"+sb1.toString()+" ("+id+")\n Vocabulary Intersection: "+(int)(m*100)+"\n Similiarity: " +(int)(sim*100)+"%\n";
                    x = x+"\n Vocabulary Intersection: "+(int)(m*100)+"\n Similiarity: " +(int)(sim*100)+"%\n";
                    flag=1;
                }
            }
             if(flag==1)
                s= s + "\n-->>"+sal.toString()+" \n" + x;
                      
        }
        resultado1.setText("");
        if(s.isEmpty()){
            resultado1.setText("Nenhuma correspondecia");
        }
        else
            resultado1.setText(s);

//        System.out.println(s);
    }
    
    
    
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        FileIN fbull = new FileIN("C:\\Users\\raulb_000\\Desktop\\3 ano\\IHC\\TextCop\\src\\bull.txt","ISO-8859-1");
        teste1 = new ArrayList<String>(Arrays.asList(fbull.readAll()));
        
        //abrir o ficheiro Racismo
        FileIN fRac = new FileIN("C:\\Users\\raulb_000\\Desktop\\3 ano\\IHC\\TextCop\\src\\Racism.txt","ISO-8859-1");
        teste2 = new ArrayList<String>(Arrays.asList(fRac.readAll()));
        
        
        

    }

}
