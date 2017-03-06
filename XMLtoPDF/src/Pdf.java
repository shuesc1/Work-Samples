package edu.xml.to.pdf;

import com.itextpdf.text.DocumentException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by lucas on 6/14/15.
 */
public class Pdf {

    private String filename;

    public Pdf(String filename) {
        this.filename = filename;
    }

    public void fromXml(String xml) {
        try {
            XmlParser xmlParser = new XmlParser(xml, filename);
            xmlParser.run();
        } catch (IOException | DocumentException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }
}
