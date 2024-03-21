package com.example.DocumentManagementSystem.service.invoiceGenerator;

import com.example.DocumentManagementSystem.dto.InvoiceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VatDetailModel {
    BigDecimal netValue;
    double vatRate;
    BigDecimal vatValue;
    BigDecimal grossValue;

    static VatDetailModel getVatDetailModel(InvoiceDto invoiceDto, VatRate vatRate){
        VatDetailModel vatDetailModel = new VatDetailModel();
        vatDetailModel.setNetValue(invoiceDto.getNetSumByVatRate(vatRate));
        vatDetailModel.setVatRate(vatRate.getRate());
        vatDetailModel.setVatValue(invoiceDto.getNetSumByVatRate(vatRate));
        vatDetailModel.setGrossValue(invoiceDto.getGrossSumByVatRate(vatRate));

        return  vatDetailModel;
    }
}
