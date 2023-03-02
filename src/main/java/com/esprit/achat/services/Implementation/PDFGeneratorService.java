package com.esprit.achat.services.Implementation;


import com.esprit.achat.persistence.entity.Facture;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.List;
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
/*
        private void writeFactureData(PdfPTable table, Facture facture) {
            table.addCell(String.valueOf(facture.getId()));
            table.addCell(String.valueOf(facture.getTotalttc()));
            table.addCell(String.valueOf(facture.getPaiements()));
            table.addCell(String.valueOf(facture.getDatefacture()));
            table.addCell(facture.getClient());
            table.addCell (facture.getAdresseclient()) ;
            table.addCell (facture.getDevise()) ;
        }

     private void writeFactureHeader (PdfPTable table) {
            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(Color.ORANGE);
            cell.setPadding(7);
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            font.setColor(Color.WHITE);
            cell.setPhrase( new Phrase("ID", font));
            table.addCell(cell);
            cell.setPhrase( new Phrase("Total", font));
            table.addCell(cell);
            cell.setPhrase( new Phrase("Paiement", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("DateFacture ", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Client ", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Adresse Client ", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Devise ", font));
            table.addCell(cell);

        }

 */
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
    table.addCell("TotalTVA");
    table.addCell(facture.getTotaltva().toString());
    table.addCell("Total Avec Remise");
    table.addCell(facture.getTotalremise().toString());
    table.addCell("Devise");
    table.addCell(facture.getDevise());
}












}


