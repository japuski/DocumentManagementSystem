package com.example.DocumentManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "invoiceFile")
public class InvoiceFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String filename;

    private byte[] file;


    @OneToOne(mappedBy = "file")
    private InvoiceEntity invoice;
}
