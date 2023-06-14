package com.example.DocumentManagementSystem.dto;

import lombok.Data;

@Data
public class ProductDto {

    private String productName;

    private String pkwiu;

    private String unit;

    private double nettoPrice;

    private double taxRate;

}
