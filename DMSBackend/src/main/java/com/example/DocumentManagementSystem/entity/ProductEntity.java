package com.example.DocumentManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productName;

    private String pkwiu;

    private String unit;

    private double nettoPrice;

    private double taxRate;


    @OneToOne(mappedBy = "product")
    private SegmentEntity segmentEntity;

}
