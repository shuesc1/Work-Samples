	import org.apache.fop.apps.FopFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.naming.spi.DirStateFactory.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.Fop;
	import org.apache.fop.apps.MimeConstants;

import com.itextpdf.text.pdf.codec.Base64.OutputStream;

public class FOP {

public static void main(String[] args){

	/*..*/

	// Step 1: Construct a FopFactory by specifying a reference to the configuration file
	// (reuse if you plan to render multiple documents!)
	FopFactory fopFactory = FopFactory.newInstance(new File("Desktop/DIGITAL_SCHOLARSHIP/ApacheFOP/fop-2.1/conf/fop.xconf"));

	// Step 2: Set up output stream.
	// Note: Using BufferedOutputStream for performance reasons (helpful with FileOutputStreams).
//	BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("Desktop/myfile.pdf")));
//	File out = new File("Desktop/myfile.pdf");
	OutputStream out = new BufferedOutputStream(new FileOutputStream(new File("Desktop/myfile.pdf")));
	
	try {
	    // Step 3: Construct fop with desired output format
	    Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

	    // Step 4: Setup JAXP using identity transformer
	    TransformerFactory factory = TransformerFactory.newInstance();
	    Transformer transformer = factory.newTransformer(); // identity transformer

	    // Step 5: Setup input and output for XSLT transformation
	    // Setup input stream
	    Source src = new StreamSource(new File("scalar.xml"));

	    // Resulting SAX events (the generated FO) must be piped through to FOP
	    SAXResult res = new SAXResult(fop.getDefaultHandler());

	    // Step 6: Start XSLT transformation and FOP processing
	    transformer.transform(src, res);

	} finally {
	    //Clean-up
	    out.close();
	}
	

}
}

