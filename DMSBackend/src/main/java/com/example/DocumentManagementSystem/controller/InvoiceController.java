package com.example.DocumentManagementSystem.controller;

import com.example.DocumentManagementSystem.dto.InvoiceDto;
import com.example.DocumentManagementSystem.entity.InvoiceEntity;
import com.example.DocumentManagementSystem.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addInvoice")
    public void addInvoice(@RequestBody InvoiceDto invoiceDto) {
        invoiceService.addInvoice(invoiceDto);
    }

    @GetMapping("/getInvoice")
    public InvoiceEntity getInvoice(@RequestParam long invoiceId) {
        return invoiceService.getInvoice(invoiceId);

    }
    // utworzyć DTO


}
