package com.example.DocumentManagementSystem.dto;

import com.example.DocumentManagementSystem.service.invoiceGenerator.VatRate;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.apache.poi.ss.formula.functions.DMax;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class InvoiceDto {


    @Size(min = 3, max = 100)
    //@Pattern(regexp = "[a-zA-Z]*@[a-zA-Z]*")
    @NotBlank(message = "Field 'invoiceNumber' -> cannot be blank")
    private String invoiceNumber;

    //private int invoiceNumberFormat; //Enum

    @Size(max = 100)
    @NotBlank(message = "Field 'invoiceType' -> cannot be blank")
    private String invoiceType; //Enum Orginal, Copy jak obsłigiwać zmianę języka?

    @NotNull(message = "Field 'seller' -> cannot be blank")
    private PersonDto seller;


    @NotNull(message = "Field 'buyer' -> cannot be blank")
    private PersonDto buyer;

    @NotNull(message = "Field 'dateOfIssue' -> cannot be blank")
    private Date dateOfIssue;

    @NotNull(message = "Field 'dateOfSale' -> cannot be blank")
    private Date dateOfSale;

    @NotNull(message = "Field 'segments' -> cannot be blank")
    private List<SegmentDto> segments;

    @Size(max = 100)
    @NotBlank(message = "Field 'currency' -> cannot be blank")
    private String currency;

    @NotNull(message = "Field 'paymentDeadline' -> cannot be blank")
    private Date paymentDeadline;

    @Size(max = 100)
    @NotBlank(message = "Field 'paymentMethod' -> cannot be blank")
    private String paymentMethod;

    @Size(max = 100)
    @NotBlank(message = "Field 'accountNumber' -> cannot be blank")
    private String accountNumber;

    @Size(max = 100)
    @NotBlank(message = "Field 'bank' -> cannot be blank")
    private String bank;

    @Size(max = 100)
    @NotBlank(message = "Field 'transferComment' -> cannot be blank")
    private String transferComment;

    @DecimalMax(value = "999999.99")
    private BigDecimal amountPaid;

    public BigDecimal getNetSum(){
        return segments.stream().map(SegmentDto::getNetValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getVatSum(){
        return getGrossSum().subtract(getNetSum());
    }

    public BigDecimal getGrossSum(){
        return segments.stream().map(SegmentDto::getGrossValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getNetSumByVatRate(VatRate vatRate){
        return segments.stream().filter(segmentDto -> BigDecimal.valueOf(segmentDto.getProduct().getVatRate()).equals(BigDecimal.valueOf(vatRate.getRate()))).map(SegmentDto::getNetValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getVatSumByVatRate(VatRate vatRate){
        return segments.stream().filter(segmentDto -> BigDecimal.valueOf(segmentDto.getProduct().getVatRate()).equals(BigDecimal.valueOf(vatRate.getRate()))).map(SegmentDto::getVatValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getGrossSumByVatRate(VatRate vatRate){
        return segments.stream().filter(segmentDto -> BigDecimal.valueOf(segmentDto.getProduct().getVatRate()).equals(BigDecimal.valueOf(vatRate.getRate()))).map(SegmentDto::getGrossValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }



}
