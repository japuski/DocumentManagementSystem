package com.example.DocumentManagementSystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class InvoiceDto {

    private int invoiceNumber;
    private PersonDto seller;
    private PersonDto buyer;
    private String dateOfIssue;
    private String dateOfSale;
    private List<SegmentDto> segments;

}
