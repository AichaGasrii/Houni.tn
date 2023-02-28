package com.esprit.achat.services.Implementation;


import com.esprit.achat.persistence.entity.Facture;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.List;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

@Service
public class PDFGeneratorService {
    /*
    public void export(List<Facture> factureList, HttpServletResponse response) throws DocumentException , IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph para = new Paragraph("Facture.", fontTitle);
        para.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(para);
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);
        //table.setwidths(new float [J (2f, 3f, 3f));
        writeFactureHeader(table);
        writeFactureData(table, factureList);
        document.add(table);
        document.close();
    }

        private void writeFactureData(PdfPTable table, List<Facture>  factureList) {
        for (Facture emp : factureList) {
            table.addCell(String.valueOf(emp.getItems())); table.addCell(emp.getClient());
            table.addCell (emp.getAdresseclient()) ;
        }
        }
        private void writeFactureHeader (PdfPTable table) {
            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(Color.ORANGE);
            cell.setPadding(5);
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            font.setColor(Color.WHITE);
            cell.setPhrase( new Phrase("ID", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Facture ", font));
            table.addCell(cell);
        }

     */



}
