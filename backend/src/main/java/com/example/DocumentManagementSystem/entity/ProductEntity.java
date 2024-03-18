package com.example.DocumentManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productName;

    private String unit;

    private double netPrice;

    private double vatRate;


    @OneToOne(mappedBy = "product")
    private SegmentEntity segmentEntity;

}
