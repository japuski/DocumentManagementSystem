package com.example.DocumentManagementSystem.dto;

import com.example.DocumentManagementSystem.entity.InvoiceEntity;
import com.example.DocumentManagementSystem.entity.ProductEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class SegmentDto {

    private int quantity;

    private ProductDto product;

    public BigDecimal getVatValue() {
        return  (getGrossValue().subtract(getNetValue()))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getNetValue() {
        return (BigDecimal.valueOf(product.getNetPrice()).multiply(BigDecimal.valueOf(quantity)))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getGrossValue() {
        return (BigDecimal.valueOf(product.getNetPrice()).multiply(BigDecimal.valueOf(quantity)))
                .add (BigDecimal.valueOf(product.getNetPrice()).multiply(BigDecimal.valueOf(product.getVatRate())).multiply(BigDecimal.valueOf(quantity)))
                .setScale(2, RoundingMode.HALF_UP);
    }


}
