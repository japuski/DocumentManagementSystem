package com.example.DocumentManagementSystem.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Data
public class ProductDto {

    private String productName;

    private String unit;

    //@Digits(fraction = 2)
    private double netPrice;

    private double vatRate;


}
