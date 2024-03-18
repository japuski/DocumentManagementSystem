package com.example.DocumentManagementSystem.service.invoiceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class InvoiceSegmentModel {

    private int segmentNumber;
    private String productName;
    private int quantity;
    private String unit;
    private BigDecimal netPrice;
    private BigDecimal netValue;
    private BigDecimal vatRate;
    private BigDecimal vatValue;
    private BigDecimal grossValue;

}
