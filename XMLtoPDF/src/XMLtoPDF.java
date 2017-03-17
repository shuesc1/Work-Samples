import java.io.File;
import java.io.OutputStream;

import javax.naming.spi.DirStateFactory.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

public class XMLtoPDF {
// EXAMPLE FROM : << https://coderanch.com/t/129358/Corrupt-PDF-file-fo-PDF >>
	public static void main(String []args) throws Exception
    {
            FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());            
            OutputStream out = null;
//            out = new BufferedOutputStream(new FileOutputStream(new File("E:/software/Apache-FOP/1.pdf")));
            out = new BufferedOutputStream(new FileOutputStream(new File("ResultXML2PDF.pdf")));
            Fop fop = null;
            fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = null; // identity transformer
            transformer = factory.newTransformer();
            
            // Setup input stream
            Source src = new StreamSource(new File("E:/software/Apache-FOP/ajit.fo"));
            
            // Resulting SAX events (the generated FO) must be piped through to FOP
            SAXResult res = null;
            res = new SAXResult(fop.getDefaultHandler());            
            transformer.transform(src, res);         
            out.close();
    }
}
	

