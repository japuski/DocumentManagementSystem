package com.example.DocumentManagementSystem.utils;

import com.example.DocumentManagementSystem.dto.*;
import com.example.DocumentManagementSystem.entity.InvoiceEntity;
import com.example.DocumentManagementSystem.mapper.InvoiceMapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MockInvoiceDataUtil {

    static InvoiceMapper invoiceMapper = new InvoiceMapper();

    public static final List<InvoiceEntity> INVOICES = List.of(
            invoiceMapper.mapInvoiceDtoToInvoiceEntity(createInvoiceDto())

    );

    public static InvoiceEntity createInvoiceEntity(){
        return invoiceMapper.mapInvoiceDtoToInvoiceEntity(createInvoiceDto());
    }


    public static InvoiceDto createInvoiceDto(){

        InvoiceDto invoiceDto = new InvoiceDto();
        SegmentDto segmentDto1 = new SegmentDto();
        SegmentDto segmentDto2 = new SegmentDto();
        ProductDto productDto1 = new ProductDto();
        ProductDto productDto2 = new ProductDto();
        PersonDto seller = new PersonDto();
        PersonDto buyer = new PersonDto();
        AddressDto sellerAddressDto = new AddressDto();
        AddressDto buyerAddressDto = new AddressDto();

        invoiceDto.setInvoiceNumber("Test");
        invoiceDto.setInvoiceType("Test");
        invoiceDto.setDateOfIssue(new Date(System.currentTimeMillis()));
        invoiceDto.setDateOfSale(new Date(System.currentTimeMillis()));
        invoiceDto.setCurrency("Test");
        invoiceDto.setPaymentDeadline(new Date(System.currentTimeMillis()));
        invoiceDto.setPaymentMethod("Test");
        invoiceDto.setAccountNumber("Test");
        invoiceDto.setBank("Test");
        invoiceDto.setTransferComment("Test");
        invoiceDto.setAmountPaid(BigDecimal.ZERO);

        sellerAddressDto.setCity("Test");
        sellerAddressDto.setNumber("Test");
        sellerAddressDto.setPostalCode("Test");
        sellerAddressDto.setStreet("Test");

        buyerAddressDto.setCity("Test");
        buyerAddressDto.setNumber("Test");
        buyerAddressDto.setPostalCode("Test");
        buyerAddressDto.setStreet("Test");


        seller.setName("Test");
        seller.setNip("Test");
        seller.setAddress(sellerAddressDto);

        buyer.setName("Test");
        buyer.setNip("Test");
        buyer.setAddress(buyerAddressDto);

        invoiceDto.setSeller(seller);
        invoiceDto.setBuyer(buyer);

        productDto1.setProductName("Test1");
        productDto1.setNetPrice(100.00);
        productDto1.setVatRate(0.23);
        productDto1.setUnit("Test");

        segmentDto1.setQuantity(1);
        segmentDto1.setProduct(productDto1);

        productDto2.setProductName("Test2");
        productDto2.setNetPrice(200.00);
        productDto2.setVatRate(0.08);
        productDto2.setUnit("Test");

        segmentDto2.setQuantity(1);
        segmentDto2.setProduct(productDto2);


        invoiceDto.setSegments(List.of(segmentDto1,segmentDto2));

        return invoiceDto;

    }

}
