package com.example.DocumentManagementSystem.service.invoiceGenerator;

public enum PersonType {
    SELLER("seller"),
    BUYER("buyer");
    private String personType;

    PersonType(String personType) {
        this.personType = personType;
    }

    public String getPersonType(){
        return personType;
    }
}
