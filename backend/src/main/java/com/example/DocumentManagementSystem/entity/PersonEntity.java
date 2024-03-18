package com.example.DocumentManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "person")
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String nip;

   // private String bankAccountNumber;


    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

}
