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
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author Paulo
 */
public class PDFReader {
    
    public PDFReader () {}
 
    public String getText(File pdfFile) throws IOException {
        PDDocument doc = PDDocument.load(pdfFile);
        return new PDFTextStripper().getText(doc);
    }
    
    String getText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
