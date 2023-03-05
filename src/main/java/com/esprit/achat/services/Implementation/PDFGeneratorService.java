package com.esprit.achat.services.Implementation;


import com.esprit.achat.persistence.entity.Facture;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

@Service
public class PDFGeneratorService {
    @Autowired
    FactureServiceIMP factureServiceIMP;


    public void export(Integer factureId, HttpServletResponse response) throws DocumentException , IOException {
        Facture facture = factureServiceIMP.retrieve(factureId);
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph para = new Paragraph("Facture", fontTitle);
        para.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(para);
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);
        //table.setwidths(new float [J (2f, 3f, 3f));
        writeFactureHeader(table,facture);
        //writeFactureData(table, facture);
        document.add(table);
        document.close();
    }
    private void writeFactureHeader(PdfPTable table, Facture facture) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);
        PdfPCell cellTitle = new PdfPCell();
        cellTitle.setBackgroundColor(Color.PINK);
        cellTitle.setPadding(7);
        cellTitle.setPhrase(new Phrase("Titre", font));
        table.addCell(cellTitle);
        PdfPCell cellValue = new PdfPCell();
        cellValue.setBackgroundColor(Color.GRAY);
        cellValue.setPadding(7);
        cellValue.setPhrase(new Phrase("Valeur", font));
        table.addCell(cellValue);
        table.setHeaderRows(1);

        table.addCell("ID");
        table.addCell(facture.getId().toString());
        table.addCell("DateFacture");
        table.addCell(facture.getDatefacture().toString());
        table.addCell("Client");
        table.addCell(facture.getClient());
        table.addCell("Adresse Client");
        table.addCell(facture.getAdresseclient());
        table.addCell("TotalTTC");
        table.addCell(facture.getTotalttc().toString());
        table.addCell("Devise");
        table.addCell(facture.getDevise());
    }

}


