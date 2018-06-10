/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



//HASHMAP 


package textcop;

import hultig.io.FileIN;
import hultig.sumo.Sentence;
import hultig.sumo.Text;
import hultig.sumo.Word;
import hultig.util.Toolkit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextBuilder;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;



/**
 * FXML Controller class
 *
 * @author raulb_000
 */
public class ChoseFileController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     List<String> palavrasenc = new ArrayList();
    List<String> palavrasencsr = new ArrayList();
    List<Integer> numero = new ArrayList<Integer>();
    
    HashMap <String,Integer> vocFreq;
    HashSet <String> cat;
    private File sFile;
    hultig.sumo.Text t;
    
    private String dir;
    
    private String conteudo;
    private File diretorio;
    
    List<String> teste=new ArrayList();
    List<String> teste1= new ArrayList();
    List<String> teste2= new ArrayList();
    
    ObservableList<String> list = FXCollections.observableArrayList();
    ObservableList<String> vazia = FXCollections.observableArrayList();
    
    @FXML
    private Label file,label;

     
    @FXML
    private TextArea texto,resultado,resultado1;
    

    @FXML
    private WebView webView;
    
    WebEngine webEngine;
    
    
    StringBuilder sol = new StringBuilder();
 
    
    
     
    @FXML
    private void onSearchFileClick (ActionEvent event){
        FileChooser fc = new FileChooser();
        sFile = fc.showOpenDialog(null);
        if(sFile!=null){           
        file.setText(sFile.getName());
        dir=sFile.getAbsolutePath();
        
    
    }
    }
    
    
     
    
    
    
    
    @FXML 
    private void Pesquisa (ActionEvent event){
       
        try{
        
        /////comeca aqui
         String name = sFile.getName();
         String ext[]=name.split("\\.");
         FileIN f = new FileIN(dir);
         
      
       
                
                switch (ext[1]) {
                    case "txt":
                    {
                       
                       // List<String> myList = new ArrayList<String>(Arrays.asList(f.readAll()));
                        
                        hultig.sumo.Text x = new hultig.sumo.Text();
                        String str = x.readFile(f.getAbsolutePath(), "ISO-8859-1"); //UTF-8
                        hultig.sumo.Text tFile = new hultig.sumo.Text(str);
                        texto.setText(Toolkit.justifyText(str,100));
                        webEngine.loadContent(str);
                        //search(myList,teste);
                        searchWithHash(tFile, teste);
                        searchSentence(tFile,t,"");
                                          
                        break;
                    }
                    case "pdf":
                    {                       
                        PDFReader pdfr= new PDFReader();
                        String s = pdfr.getText(f);
                        hultig.sumo.Text tFile = new hultig.sumo.Text(s);
                        texto.setText(Toolkit.justifyText(s,100));
                        webEngine.loadContent(s);
                        List<String> myList = new ArrayList<String>(Arrays.asList(s.split(" ")));
                        //search(myList,teste);
                        searchWithHash(tFile, teste);
                        searchSentence(tFile,t,"");
                                              
                        break;
                    }
                    case "docx":
                    {                       
                        DOCXReader dcxr = new DOCXReader();
                        String l = dcxr.getText(f);
                        hultig.sumo.Text tFile = new hultig.sumo.Text(l);
                        texto.setText(Toolkit.justifyText(l,100));
                        webEngine.loadContent(l);
                        
                        List<String> myList = new ArrayList<String>(Arrays.asList(l.split(" ")));
                        //search(myList,teste);
                        searchWithHash(tFile,teste);
                        searchSentence(tFile,t,"");
                                              
                        break;
                    }
                }
                
        //// acaba aqui
           
        //engine.setJavaScriptEnabled(true);
        //engine.loadContent(HTML_STRING);
            
         
        }
        catch(Exception e){
            System.out.println("Nao leu o ficheiro");
        }         
    } 
    
    
    
   
    private void FindAndColor(){
        
        String st=texto.getText().toLowerCase();
        StringBuilder sb= new StringBuilder(
                "<!DOCTYPE html>"
                +"<head>"
                + "<style>"
                +"pink{"  
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
        hultig.sumo.Text x=new Text(st);
        StringBuilder rs=new StringBuilder();
        for (Sentence s : x) {
            for (Word w : s) {
                int i;
                for (i = 0; i < teste.size(); i++) {
                    if (w.equals(teste.get(i))) {
                        Word wx;
                        if(teste1.contains(w.toString())){
                            wx= new Word("<pink>"+w+"</pink>");
                        }
                        else if(teste2.contains(w.toString()))
                        {
                            wx = new Word("<blue>" + w + "</blue>");
                        }
                        else {   
                            wx= new Word("<green>"+w+"</green>");
                        }
                        sb.append(" "+wx.toString());
                        break;
                    }
                }
                if(i==teste.size()){
                    sb.append(" "+w);
                }

            }
        }
        sb.append(" </p>"
                + "</body>"
                + "</html>");
        
        
        webEngine.loadContent(sb.toString());
       
    }
    
    
    
     

   //procurar por frases
    public void searchSentence(hultig.sumo.Text ta,hultig.sumo.Text tb,String id){
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
                   // x = x+"->"+sb1.toString()+" ("+id+")\n Vocabulary Intersection: "+(int)(m*100)+"\n Similiarity: " +(int)(sim*100)+"%\n";
                    x = x+"\n Vocabulary Intersection: "+(int)(m*100)+"\n Similiarity: " +(int)(sim*100)+"%\n";
                    flag=1;
                    //System.out.println(sal.toString());
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
        
        FindAndColor();
       // System.out.println(s);
    }
    
    //procura usando HashMap
    public void searchWithHash(hultig.sumo.Text tex,List<String> proibidas) {  
        int contap;
        list.clear();
        vocFreq = new HashMap<String, Integer>();
        hultig.sumo.Text te = new hultig.sumo.Text(tex.toString().toLowerCase());
        for (Sentence s : te) {
            for (Word w : s) {              
                for (int i = 0; i < proibidas.size(); i++) {
                    if (w.equals(proibidas.get(i))) {
                                             
                        Integer F = vocFreq.get(proibidas.get(i));
                        if (F == null) {
                            vocFreq.put(w.toString(), 1);
                        } else {
                            vocFreq.put(w.toString(), F + 1);
                        }

                    }
                }

            }
        }

        resultado.setText("");
        contap = vocFreq.size();
        if (contap == 1) {
            resultado.setText("\n"+ "Foram encontradas: 1 % de palavras proibidas." + "\n");
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
                resultado.setText("\n" + "Foram encontradas: " + res[0] + " % de palavras proibidas :" + "\n");
            }
        }
        if (vocFreq.isEmpty()) {
            resultado.setText("Nenhuma correspondecia");
        } else {
            for (String key : vocFreq.keySet()) {
                list.add(key);
                resultado.setText(resultado.getText() + "\n" + key + ": " + vocFreq.get(key) + "\n");
                //System.out.println(key);
                //System.out.println(vocFreq.get(key));
               
               
            }
        }
        
        
        te.clear();
       
    }
 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //abrir o ficheiro das palavras
         webEngine = webView.getEngine();
        
        FileIN fpalavras = new FileIN("C:\\Users\\raulb_000\\Desktop\\3 ano\\IHC\\TextCop\\src\\pesquisa.txt","ISO-8859-1");

        teste = new ArrayList<String>(Arrays.asList(fpalavras.readAll()));
        
        resultado.setText("Palavras ofensivas:"+"\n"+"\n");
        for(int i=0;i<teste.size();i++){
           
            resultado.setText(resultado.getText()+teste.get(i)+ ",  ");
            
        }
        
        

        //abrir o ficheiros das frases
        FileIN ffrases = new FileIN("C:\\Users\\raulb_000\\Desktop\\3 ano\\IHC\\TextCop\\src\\pesquisafrases.txt","ISO-8859-1");
        String[] linhas = ffrases.readAll();

        StringBuilder sb = new StringBuilder();
        for (String linha : linhas) {
            resultado1.setText(resultado1.getText()+"\n"+linha);
            sb.append(linha).append(' ');
        }

        t = new hultig.sumo.Text(sb.toString().toLowerCase());
        
        //abrir o ficheiro Bull
        FileIN fbull = new FileIN("C:\\Users\\raulb_000\\Desktop\\3 ano\\IHC\\TextCop\\src\\bull.txt","ISO-8859-1");
        teste1 = new ArrayList<String>(Arrays.asList(fbull.readAll()));
        
        //abrir o ficheiro Racismo
        FileIN fRac = new FileIN("C:\\Users\\raulb_000\\Desktop\\3 ano\\IHC\\TextCop\\src\\Racism.txt","ISO-8859-1");
        teste2 = new ArrayList<String>(Arrays.asList(fRac.readAll()));
        
        
        
        
        
    }
    
    
}    
    

