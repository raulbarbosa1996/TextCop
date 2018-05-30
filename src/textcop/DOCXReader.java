/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textcop;

/**
 *
 * @author raulb_000
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 *
 * @author Paulo
 */
public class DOCXReader {
    public DOCXReader() {}
    
    public String getText(File docxFile) throws FileNotFoundException, IOException{
        FileInputStream fis = new FileInputStream(docxFile.getAbsolutePath());	
        XWPFDocument doc = new XWPFDocument(fis); 
        XWPFWordExtractor ex = new XWPFWordExtractor(doc); 
        String text = ex.getText(); 
        
        return text;
    }
}
