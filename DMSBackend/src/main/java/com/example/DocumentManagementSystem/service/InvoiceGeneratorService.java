package com.example.DocumentManagementSystem.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InvoiceGeneratorService {

//    public static void main(String[] args) throws IOException {
//
//        Person seller = new Person();
//        Person buyer = new Person();
//
//        seller.setName("Firma ABC");
//        seller.setAdress(Adress.builder()
//                .city("Gdańsk")
//                .number1("23A")
//                .number2("28")
//                .postalCode("80-333")
//                .street("Piastowska")
//                .build()
//        );
//
//        buyer.setName("Firma BCD");
//        buyer.setAdress(Adress.builder()
//                .city("Warszawa")
//                .number1("222A")
//                .number2("")
//                .postalCode("00-120")
//                .street("Niepodległości")
//                .build()
//        );
//
//        Invoice invoice = new Invoice();
//        invoice.setInvoiceNumber(1);
//        invoice.setType("ORYGINAŁ");
//        invoice.setBuyer(buyer);
//        invoice.setSeller(seller);
//        invoice.setDateOfIssue("2023-03-12");
//        invoice.setDateOfSale("2023-03-12");
//
//        Service service1 =
//                Service.builder()
//                        .serviceName("Usługa 1")
//                        .count(5)
//                        .pkwiu("")
//                        .nettoPrice(50)
//                        .measure("szt.")
//                        .taxRate(0.23)
//                        .build()
//                ;
//
//        Service service2 =
//                Service.builder()
//                        .serviceName("Usługa 2")
//                        .count(20)
//                        .pkwiu("")
//                        .nettoPrice(10)
//                        .measure("szt.")
//                        .taxRate(0.23)
//                        .build()
//                ;
//
//        List<Service> services = new ArrayList<>();
//        services.add(service1);
//        services.add(service2);
//        invoice.setServices(services);
//
//        //create document
//        XWPFDocument document = new XWPFDocument();
//
//        XWPFParagraph paragraph = document.createParagraph();
//        paragraph.setAlignment(ParagraphAlignment.RIGHT);
//
//        XWPFRun run = paragraph.createRun();
//        run.setText("Faktura VAT nr: " + invoice.getInvoiceNumber());
//        run.setFontFamily("Arial");
//        run.setFontSize(12);
//        run.setBold(true);
//        run.setColor("000000");
//        run.addBreak();
//        XWPFRun run2 = paragraph.createRun();
//        run2.setText(invoice.getType());
//        run2.addBreak();
//        run2.setText("Data sprzedaży: " + invoice.getDateOfSale());
//        run2.addBreak();
//        run2.setText("Data wystawienia: " + invoice.getDateOfIssue());
//
//
//
//        // seller , buyer
//
//        // create table1
//
//        XWPFTable table1 = document.createTable(1, 2);
//        table1.setCellMargins(10,100,10,100);
//        table1.setTableAlignment(TableRowAlign.CENTER);
//
//        // seller
//
//        XWPFParagraph sellerParagraph = table1.getRow(0).getCell(0).addParagraph();
//        // usuwanie domyślnego paragrafu
//        table1.getRow(0).getCell(0).removeParagraph(0);
//
//
//        XWPFRun sellerRun1 = sellerParagraph.createRun();
//
//        sellerRun1.setText("Sprzedawca:");
//        sellerRun1.addBreak();
//        sellerRun1.setText(seller.getName());
//        sellerRun1.addBreak();
//        sellerRun1.setText(seller.getAdress().street + " " + seller.getAdress().number1 + "/" + seller.getAdress().number2);
//        sellerRun1.addBreak();
//        sellerRun1.setText(seller.getAdress().postalCode + " " + seller.getAdress().city);
//
//
//        //buyer
//
//        XWPFParagraph buyerParagraph = table1.getRow(0).getCell(1).addParagraph();
//
//        table1.getRow(0).getCell(1).removeParagraph(0);
//
//        XWPFRun buyerRun1 = buyerParagraph.createRun();
//
//        buyerRun1.setText("Nabywca:");
//        buyerRun1.addBreak();
//        buyerRun1.setText(buyer.getName());
//        buyerRun1.addBreak();
//        buyerRun1.setText(buyer.getAdress().street + " " + buyer.getAdress().number1 + "/" + buyer.getAdress().number2);
//        buyerRun1.addBreak();
//        buyerRun1.setText(buyer.getAdress().postalCode + " " + buyer.getAdress().city);
//
//        // create empty space
//
//        XWPFRun emptySpace = document.createParagraph().createRun();
//        emptySpace.addBreak();
//
//
//        //create table2
//
//
//        XWPFTable table2 = document.createTable(1 + invoice.getServices().size(), 9);
//        table2.setCellMargins(10,100,10,100);
//
//
//
//        //set cell values
//        table2.getRow(0).getCell(0).setText("Lp.");
//        table2.getRow(0).getCell(1).setText("Nazwa towaru/usługi");
//        table2.getRow(0).getCell(2).setText("PKWiU");
//        table2.getRow(0).getCell(3).setText("Ilość");
//        table2.getRow(0).getCell(4).setText("j.m.");
//        table2.getRow(0).getCell(5).setText("Cena netto");
//        table2.getRow(0).getCell(6).setText("VAT");
//        table2.getRow(0).getCell(7).setText("Wartość netto");
//        table2.getRow(0).getCell(8).setText("Wartość brutto");
//
//        for (int i = 0; i < invoice.getServices().size();i++) {
//
//            table2.getRow(i+1).getCell(0).setText(String.valueOf(i+1));
//            table2.getRow(i+1).getCell(1).setText(invoice.getServices().get(i).getServiceName());
//            table2.getRow(i+1).getCell(2).setText("");
//            table2.getRow(i+1).getCell(3).setText(String.valueOf(invoice.getServices().get(i).getCount()));
//            table2.getRow(i+1).getCell(4).setText("szt.");
//            table2.getRow(i+1).getCell(5).setText(String.valueOf(invoice.getServices().get(i).getNettoPrice()));
//            table2.getRow(i+1).getCell(6).setText(String.valueOf(invoice.getServices().get(i).getTaxRate()));
//            table2.getRow(i+1).getCell(7).setText(String.valueOf(invoice.getServices().get(i).getTotalNettoValue()));
//            table2.getRow(i+1).getCell(8).setText(String.valueOf(invoice.getServices().get(i).getTotalBruttoValue()));
//
//        }
//
//
//
//        // create empty space
//
//        XWPFRun emptySpace2 = document.createParagraph().createRun();
//        emptySpace2.addBreak();
//
//
//        //create table3
//
//        XWPFTable table3 = document.createTable(3, 4);
//        table3.setTableAlignment(TableRowAlign.RIGHT);
//        table3.setCellMargins(10,100,10,100);
//
//
//
//        table3.getRow(0).getCell(0).setText("VAT");
//        table3.getRow(0).getCell(1).setText("Wartość netto");
//        table3.getRow(0).getCell(2).setText("Kwota VAT");
//        table3.getRow(0).getCell(3).setText("Wartość brutto");
//
//        table3.getRow(1).getCell(0).setText("23%");
//        table3.getRow(1).getCell(1).setText(String.valueOf(invoice.getTotalPriceNETTO()));
//        table3.getRow(1).getCell(2).setText(String.valueOf(invoice.getTotalVAT()));
//        table3.getRow(1).getCell(3).setText(String.valueOf(invoice.getTotalPriceBRUTTO()));
//
//        table3.getRow(2).getCell(0).setText("Razem:");
//        table3.getRow(2).getCell(1).setText(String.valueOf(invoice.getTotalPriceNETTO()));
//        table3.getRow(2).getCell(2).setText(String.valueOf(invoice.getTotalVAT()));
//        table3.getRow(2).getCell(3).setText(String.valueOf(invoice.getTotalPriceBRUTTO()));
//
//        // create empty space
//
//        XWPFRun emptySpace3 = document.createParagraph().createRun();
//        emptySpace3.addBreak();
//
//        //create table4
//
//        XWPFTable table4 = document.createTable(3, 2);
//        table4.setTableAlignment(TableRowAlign.LEFT);
//        table4.setCellMargins(10,100,10,100);
//
//        table4.getRow(0).getCell(0).setText("Zapłacono:");
//        table4.getRow(1).getCell(0).setText("Razem do zapłaty:");
//        table4.getRow(2).getCell(0).setText("Pozostało do zapłaty:");
//
//        table4.getRow(0).getCell(1).setText("");
//        table4.getRow(1).getCell(1).setText(String.valueOf(invoice.getTotalPriceBRUTTO()));
//        table4.getRow(2).getCell(1).setText(String.valueOf(invoice.getTotalPriceBRUTTO()));
//
//        // create empty space
//
//        XWPFRun emptySpace4 = document.createParagraph().createRun();
//        emptySpace4.addBreak();
//
//        //create table5
//
//        XWPFTable table5 = document.createTable(3, 2);
//        table5.setTableAlignment(TableRowAlign.LEFT);
//        table5.setCellMargins(10,100,10,100);
//
//        table5.getRow(0).getCell(0).setText("Forma płatności:");
//        table5.getRow(1).getCell(0).setText("Konto:");
//        table5.getRow(2).getCell(0).setText("Termin płatności:");
//
//        table5.getRow(0).getCell(1).setText("Przelew");
//        table5.getRow(1).getCell(1).setText("1232 1231 12312 12312 12312");
//        table5.getRow(2).getCell(1).setText("21-11-2023");
//
//        //write document
//        FileOutputStream out = new FileOutputStream("table3.docx");
//        document.write(out);
//        out.close();
//
//    }

}
