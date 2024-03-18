package com.example.DocumentManagementSystem.mapper;

import com.example.DocumentManagementSystem.dto.InvoiceDto;
import com.example.DocumentManagementSystem.entity.InvoiceEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InvoiceMapper {

    public InvoiceEntity mapInvoiceDtoToInvoiceEntity(InvoiceDto invoiceDto){

        InvoiceEntity invoiceEntity = new InvoiceEntity();

        invoiceEntity.setInvoiceNumber(invoiceDto.getInvoiceNumber());
        invoiceEntity.setInvoiceType(invoiceDto.getInvoiceType());
        invoiceEntity.setDateOfIssue(invoiceDto.getDateOfIssue());
        invoiceEntity.setDateOfSale(invoiceDto.getDateOfSale());
        invoiceEntity.setSeller(new PersonMapper().mapPersonDtoToPersonEntity(invoiceDto.getSeller()));
        invoiceEntity.setBuyer(new PersonMapper().mapPersonDtoToPersonEntity(invoiceDto.getBuyer()));
        invoiceEntity.setSegments(invoiceDto.getSegments().stream().map(segmentDto -> new SegmentMapper().mapSegmentDtoToSegmentEntity(segmentDto,invoiceEntity)).toList());
        invoiceEntity.setCurrency(invoiceDto.getCurrency());
        invoiceEntity.setPaymentDeadline(invoiceDto.getPaymentDeadline());
        invoiceEntity.setPaymentMethod(invoiceDto.getPaymentMethod());
        invoiceEntity.setAccountNumber(invoiceDto.getAccountNumber());
        invoiceEntity.setBank(invoiceDto.getBank());
        invoiceEntity.setTransferComment(invoiceDto.getTransferComment());
        invoiceEntity.setAmountPaid(invoiceDto.getAmountPaid());

        return invoiceEntity;
    }

    public InvoiceDto mapInvoiceEntityToInvoiceDtoTo(InvoiceEntity invoiceEntity){

        InvoiceDto invoiceDto = new InvoiceDto();

        invoiceDto.setInvoiceNumber(invoiceEntity.getInvoiceNumber());
        invoiceDto.setInvoiceType(invoiceEntity.getInvoiceType());
        invoiceDto.setDateOfIssue(invoiceEntity.getDateOfIssue());
        invoiceDto.setDateOfSale(invoiceEntity.getDateOfSale());
        invoiceDto.setSeller(new PersonMapper().mapPersonEntityToPersonDto(invoiceEntity.getSeller()));
        invoiceDto.setBuyer(new PersonMapper().mapPersonEntityToPersonDto(invoiceEntity.getBuyer()));
        invoiceDto.setSegments(invoiceEntity.getSegments().stream().map(segmentEntity -> new SegmentMapper().mapSegmentEntityToSegmentDto(segmentEntity)).toList());
        invoiceDto.setCurrency(invoiceEntity.getCurrency());
        invoiceDto.setPaymentDeadline(invoiceEntity.getPaymentDeadline());
        invoiceDto.setPaymentMethod(invoiceEntity.getPaymentMethod());
        invoiceDto.setAccountNumber(invoiceEntity.getAccountNumber());
        invoiceDto.setBank(invoiceEntity.getBank());
        invoiceDto.setTransferComment(invoiceEntity.getTransferComment());
        invoiceDto.setAmountPaid(invoiceEntity.getAmountPaid());

        return invoiceDto;
    }

}
