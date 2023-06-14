package com.example.DocumentManagementSystem.service;

import com.example.DocumentManagementSystem.dto.InvoiceDto;
import com.example.DocumentManagementSystem.entity.*;
import com.example.DocumentManagementSystem.mapper.*;
import com.example.DocumentManagementSystem.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    @Transactional
    public void addInvoice(InvoiceDto invoiceDto){

       invoiceRepository.save(invoiceMapper.mapInvoiceDtoToInvoiceEntity(invoiceDto));

    }

    public InvoiceEntity getInvoice(long invoiceId){

        InvoiceDto invoiceDto;

        InvoiceEntity invoice = invoiceRepository.findById(invoiceId).get();
        log.error(String.valueOf(invoice.getSegments().stream().findFirst().orElseThrow().getQuantity()));

        //log.error(String.valueOf(invoice.getBuyer().getId()));

        return invoice;
    }



}
