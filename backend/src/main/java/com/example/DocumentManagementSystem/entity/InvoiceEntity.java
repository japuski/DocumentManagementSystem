package com.example.DocumentManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "invoice")
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String invoiceNumber;

    private String invoiceType;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "id")
    private PersonEntity seller;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "id")
    private PersonEntity buyer;

    private Date dateOfIssue;

    private Date dateOfSale;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "invoice", cascade = CascadeType.PERSIST )
    private List<SegmentEntity> segments;

    @Lob
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "file_id")
    private InvoiceFileEntity file;

    private String currency;

    private String paymentMethod;

    private Date paymentDeadline;

    private String accountNumber;

    private String bank;

    private String transferComment;

    private BigDecimal amountPaid;


}
