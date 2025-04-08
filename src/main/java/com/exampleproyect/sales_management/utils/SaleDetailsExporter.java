package com.exampleproyect.sales_management.utils;

import java.awt.Color;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import com.exampleproyect.sales_management.domain.models.Invoice;
import com.exampleproyect.sales_management.dto.UserDto;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

public class SaleDetailsExporter {

    private static final Color HEADER_COLOR = Color.getHSBColor(118,0.0f, 0.65f);


    private Invoice invoice;

    public SaleDetailsExporter(Invoice invoice) {
        invoice.setBusinessName("Business Name");
        invoice.setBusinessEmail("businessemail@mail.com");
        invoice.setBusinessAddress("999 - Quilmes - Buenos Aires");
        invoice.setBusinessPhoneNumber("888-999-0000");
        invoice.setBusinessWebsite("mybusinesswebpage.com");

        this.invoice = invoice;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    private void buildHeader(PdfWriter writer, Document doc) {

        PdfPTable table = new PdfPTable(3);
        table.setTotalWidth(510);
        table.setWidths(new int[]{38, 36, 36});

        //default settings for all cells - EXCLUDES PdfPCELL's
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setPaddingBottom(5);
        table.getDefaultCell().setBorder(Rectangle.BOTTOM);

        //Cell used for separation
        PdfPCell emptyCell = new PdfPCell(new Paragraph(""));
        emptyCell.setBorder(Rectangle.NO_BORDER);

        table.addCell(emptyCell);
        Paragraph title =  new Paragraph(invoice.getBusinessName(), new Font(Font.HELVETICA, 20, Font.BOLD));
        PdfPCell titleCell = new PdfPCell(title);
        titleCell.setPaddingBottom(10);
        titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        titleCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(titleCell);
        table.addCell(emptyCell);

        Font cellFont = new Font(Font.HELVETICA, 8);
        table.addCell(new Paragraph("Phone Number: " + invoice.getBusinessPhoneNumber(), cellFont));
        table.addCell(new Paragraph("Address : " + invoice.getBusinessAddress(), cellFont));
        table.addCell(new Paragraph("Website : " + invoice.getBusinessWebsite(), cellFont));

        table.writeSelectedRows(0, -1, 34, 828, writer.getDirectContent());
    }

    private void buildInvoiceDetails(PdfWriter writer, Document doc) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedDate = invoice.getSale().getDate().format(formatter);

        PdfPTable table = new PdfPTable(2);
        table.setTotalWidth(200);
        table.setWidths(new int[]{30, 60});

        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        PdfPCell idHeaderCell = new PdfPCell(new Paragraph("Invoice ID", new Font(Font.HELVETICA, 10)));
        idHeaderCell.setBorder(Rectangle.BOTTOM);
        idHeaderCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

        PdfPCell dateHeaderCell = new PdfPCell(new Paragraph("Date", new Font(Font.HELVETICA, 10)));
        dateHeaderCell.setBorder(Rectangle.BOTTOM);
        dateHeaderCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

        table.addCell(idHeaderCell);
        table.addCell(dateHeaderCell);
        table.addCell(new Paragraph("#" + String.valueOf(invoice.getSale().getId())));
        table.addCell(new Paragraph(formattedDate));


        table.writeSelectedRows(0, -1, 344, 750, writer.getDirectContent());
        
    }

    private void buildUserDetails(PdfWriter writer, Document doc) {
        UserDto user = invoice.getSale().getUser();

        PdfPTable table = new PdfPTable(2);
        table.setTotalWidth(250);
        table.setWidths(new int[]{50, 80});
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        
        PdfPCell headerCell = new PdfPCell(new Paragraph("BILL TO"));
        headerCell.setBorder(Rectangle.NO_BORDER);
        headerCell.setBackgroundColor(HEADER_COLOR);
     
        table.addCell(headerCell);
        headerCell.setPhrase(new Phrase(""));
        table.addCell(headerCell);

        table.addCell("Full name: ");
        table.addCell(user.getName() + " " + user.getLastname());

        
        table.addCell("Email adress: ");
        table.addCell(user.getEmail());

        table.writeSelectedRows(0, -1, 34, 700, writer.getDirectContent());

    }



    private void buildFooter(PdfWriter writer, Document doc) {
        PdfPTable table = new PdfPTable(2);
        table.setTotalWidth(510);
        table.setWidths(new int[]{50, 50});
        table.getDefaultCell().setPaddingBottom(5);
        table.getDefaultCell().setBorder(Rectangle.TOP);

        Paragraph title =  new Paragraph("Invoice Template @ 2025", new Font(Font.HELVETICA, 10));
        PdfPCell titleCell = new PdfPCell(title);
        titleCell.setPaddingTop(4);
        titleCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        titleCell.setBorder(Rectangle.TOP);
        table.addCell(titleCell);

        Paragraph emailText = new Paragraph("For any questions contact us at: " + invoice.getBusinessEmail(), new Font(Font.HELVETICA, 10));
        PdfPCell emailCell = new PdfPCell(emailText);
        emailCell.setPaddingTop(4);
        emailCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        emailCell.setBorder(Rectangle.TOP);
        table.addCell(emailCell);

        table.writeSelectedRows(0, -1, 34, 36, writer.getDirectContent());
    }


    private PdfPTable buildTable() {

        PdfPTable table = new PdfPTable(4);

        table.setWidthPercentage(100f);
        table.setSpacingBefore(150f);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setWidths(new int[]{50,25,25,25});


        writeTableHeader(table);
        writeTableData(table);
        writeTableFooter(table);
        
        return table;
    }
    
    private void writeTableHeader(PdfPTable table) {

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(HEADER_COLOR);

        cell.setPadding(5f);
        
        cell.setBorder(5); // TOP = 1 + LEFT = 4
        cell.setPhrase(new Phrase("Product name"));
        table.addCell(cell);

        cell.setBorder(Rectangle.TOP);
        cell.setPhrase(new Phrase("Price"));
        table.addCell(cell);

        cell.setBorder(Rectangle.TOP);
        cell.setPhrase(new Phrase("Quantity"));
        table.addCell(cell);

        cell.setBorder(9); // TOP = 1 + RIGHT = 8
        cell.setPhrase(new Phrase("Total ($)"));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {

        PdfPCell defCell = table.getDefaultCell();

        invoice.getSale().getSalesDetails().forEach(saleDetail -> {

            defCell.setBorder(Rectangle.LEFT);
            table.addCell(saleDetail.getProduct().getName());

            defCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(String.valueOf(saleDetail.getProduct().getPrice()));

            defCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(String.valueOf(saleDetail.getQuantity()));

            defCell.setBorder(Rectangle.RIGHT);
            table.addCell(String.valueOf(saleDetail.calcTotal()));
        });
        
    }

    private void writeTableFooter(PdfPTable table) {

        PdfPCell messageCell = new PdfPCell(new Phrase("Thanks for buying"));
        messageCell.setColspan(2);
        messageCell.setBorder(7);
        messageCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(messageCell);

        
        PdfPCell footerCell = new PdfPCell();
        footerCell.setPhrase(new Phrase("Total ($)"));
        footerCell.setBorder(7); // Left = 4 + Bottom = 2 + Top = 1
        table.addCell(footerCell);
        footerCell.setBorder(11); //RIGHT = 8 + BOTTOM = 2 + top = 1
        footerCell.setPhrase(new Phrase(String.valueOf(invoice.calcTotal())));
        table.addCell(footerCell);
    }

    public void export(HttpServletResponse response) throws IOException {

        Document doc = new Document(PageSize.A4, 36, 36, 65, 36);

        PdfWriter pdfWriter = PdfWriter.getInstance(doc, response.getOutputStream());

        doc.open();
        
        // Invoice static elements
        buildHeader(pdfWriter, doc);
        buildInvoiceDetails(pdfWriter, doc);
        buildUserDetails(pdfWriter, doc);
        buildFooter(pdfWriter, doc);

        doc.add(new Paragraph("\n\n"));
        doc.add(buildTable());

        doc.close();
        pdfWriter.close();

    }


}
