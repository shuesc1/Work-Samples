package edu.xml.to.pdf;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import sun.font.FontFamily;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.awt.Font.MONOSPACED;

/**
 * Created by lucas on 6/14/15.
 */
public class PdfBuilder {

    Document document;

    public PdfBuilder(String filename) throws IOException, DocumentException {
        document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();
    }

    public void writeLine(String content, int level) throws DocumentException {
        String l = "";
        for (int i = 0; i < level; i++) l += "        ";

        Chunk chunk = new Chunk("  " + l + content);
        document.add(new Paragraph(chunk));
    }

    public void finish() {
        document.close();
    }
}
