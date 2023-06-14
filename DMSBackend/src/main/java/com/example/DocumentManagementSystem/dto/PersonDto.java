package com.example.DocumentManagementSystem.dto;

import lombok.Data;

@Data
public class PersonDto {

    private String name;

    private String nip;

    // private String bankAccountNumber;

    private AddressDto address;

}
