package com.example.DocumentManagementSystem.service.invoiceGenerator;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public enum VatRate {
    VAT_23(0.23),
    VAT_8(0.08),
    VAT_5(0.05),
    VAT_0(0.0);

    private double rate;

    VatRate(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
