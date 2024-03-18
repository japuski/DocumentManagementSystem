package com.example.DocumentManagementSystem.service.invoiceGenerator;

import com.example.DocumentManagementSystem.dto.InvoiceDto;
import com.example.DocumentManagementSystem.dto.SegmentDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InvoiceSegmentModelMapper {

    public InvoiceSegmentModel mapSegmentDtoToInvoiceSegmentModel (int segmentNumber, SegmentDto segmentDto){

        InvoiceSegmentModel invoiceSegmentModel = new InvoiceSegmentModel();

        invoiceSegmentModel.setSegmentNumber(segmentNumber + 1);
        invoiceSegmentModel.setProductName(segmentDto.getProduct().getProductName());
        invoiceSegmentModel.setQuantity(segmentDto.getQuantity());
        invoiceSegmentModel.setUnit(segmentDto.getProduct().getUnit());
        invoiceSegmentModel.setNetPrice(BigDecimal.valueOf(segmentDto.getProduct().getNetPrice()));
        invoiceSegmentModel.setNetValue(segmentDto.getNetValue());
        invoiceSegmentModel.setVatRate(BigDecimal.valueOf(segmentDto.getProduct().getVatRate()));
        invoiceSegmentModel.setVatValue(segmentDto.getVatValue());
        invoiceSegmentModel.setGrossValue(segmentDto.getGrossValue());


        return invoiceSegmentModel;
    }
}
