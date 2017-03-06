package edu.xml.to.pdf;

import com.itextpdf.text.DocumentException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by lucas on 6/14/15.
 */
public class XmlParser implements Runnable {

    private PdfBuilder pdfBuilder;
    private Document document;

    private Document getDocument(String xml) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));

        document.getDocumentElement().normalize();

        return document;
    }

    public XmlParser(String xml, String filename) throws IOException, DocumentException, ParserConfigurationException, SAXException {
        document = getDocument(xml);
        pdfBuilder = new PdfBuilder(filename);
    }

    @Override
    public void run() {
        try {
            NodeList nodeList = document.getDocumentElement().getChildNodes();
            printAll(nodeList, 0);
            pdfBuilder.finish();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void printAll(NodeList nodeList, int level) throws DocumentException {
        if (nodeList == null) return;
        if (nodeList.getLength() == 0) return;

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String name = element.getTagName();
                String value = element.getFirstChild().getNodeValue();

                if (value.contains("\n")) value = "";

                pdfBuilder.writeLine(name + ": " + value, level);

                NodeList nodeListAux = node.getChildNodes();
                printAll(nodeListAux, level + 1);
            }
        }
    }
}
