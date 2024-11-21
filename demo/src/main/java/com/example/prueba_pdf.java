package com.example;

import java.io.FileOutputStream;

import com.itextpdf.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;


public class prueba_pdf {
   static Document documento;
    static FileOutputStream fileOutputStream;
    static Font fuente = FontFactory.getFont(FontFactory.COURIER);
        public static void main(String[] args) throws IOException, DocumentException {
            documento = new Document(PageSize.A4);
    
            String ruta = System.getProperty("user.home");
            fileOutputStream = new FileOutputStream(ruta+"/Escritorio/Reporte.pdf");
    
            PdfWriter.getInstance(documento, fileOutputStream);
    
            documento.open();
            PdfPTable tablaTitulo = new PdfPTable(1);
            PdfPCell celdaTitulo = new PdfPCell(new Phrase("HOLA MUNDO, SOY EL PATRÓN",fuente));
            celdaTitulo.setColspan(5);
            celdaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            documento.add(tablaTitulo);

            Paragraph parrafo = new Paragraph();
            parrafo.add(new Phrase("Este es un ejemplo de cómo crear un reporte en PDF con IText", fuente));
            documento.add(parrafo);

            PdfPTable tablaEmpleados=new PdfPTable(4);
            tablaEmpleados.addCell("Nombre");
            tablaEmpleados.addCell("Dia del Registro");
            tablaEmpleados.addCell("Horas Registradas");
            tablaEmpleados.addCell("Total de horas");


            documento.add(tablaEmpleados);

            documento.close();
    }

}
