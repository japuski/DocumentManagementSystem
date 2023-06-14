package com.example.DocumentManagementSystem.mapper;

import com.example.DocumentManagementSystem.dto.InvoiceDto;
import com.example.DocumentManagementSystem.entity.InvoiceEntity;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper {

    public InvoiceEntity mapInvoiceDtoToInvoiceEntity(InvoiceDto invoiceDto){

        InvoiceEntity invoiceEntity = new InvoiceEntity();

        invoiceEntity.setInvoiceNumber(invoiceDto.getInvoiceNumber());
        invoiceEntity.setDateOfIssue(invoiceDto.getDateOfIssue());
        invoiceEntity.setDateOfSale(invoiceDto.getDateOfSale());
        invoiceEntity.setSeller(new PersonMapper().mapPersonDtoToPersonEntity(invoiceDto.getSeller()));
        invoiceEntity.setBuyer(new PersonMapper().mapPersonDtoToPersonEntity(invoiceDto.getBuyer()));
        invoiceEntity.setSegments(invoiceDto.getSegments().stream().map(segmentDto -> new SegmentMapper().mapSegmentDtoToSegmentEntity(segmentDto,invoiceEntity)).toList());

        return invoiceEntity;
    }

}
