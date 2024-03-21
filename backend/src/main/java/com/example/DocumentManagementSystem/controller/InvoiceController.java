package com.example.DocumentManagementSystem.controller;

import com.example.DocumentManagementSystem.dto.InvoiceDto;
import com.example.DocumentManagementSystem.entity.InvoiceEntity;
import com.example.DocumentManagementSystem.service.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    //Security logowanie (tabele user, userType, klucz obcy w invoice do user)

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addInvoice")
    public void addInvoice(@RequestBody @Valid InvoiceDto invoiceDto) throws JRException, IOException {
        invoiceService.addInvoice(invoiceDto);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getInvoice")
    public InvoiceDto getInvoice(@RequestParam long invoiceId) {
        return invoiceService.getInvoiceDto(invoiceId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getInvoicesList")
    public List<InvoiceDto> getInvoices() {
        return invoiceService.getInvoiceDtoList();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getInvoiceFile")
    public ResponseEntity<byte[]> getInvoiceFile(@RequestParam long invoiceId) {
        return invoiceService.getFile(invoiceId);
    }



    //    //generate File
//    @CrossOrigin(origins = "http://localhost:4200")
//    @PostMapping("/generateInvoice")
//    public void createInvoice(@RequestBody long invoiceId) throws IOException {
//
//        InvoiceDto invoiceDto = invoiceService.getInvoiceDto(invoiceId);
//
//    }

    //    @GetMapping("/getInvoices")
//    public Page<InvoiceDto> getInvoices(@RequestParam Integer pageNumber,
//                                           @RequestParam Integer pageSize) {
//        return invoiceService.getInvoices(pageNumber, pageSize);
//    }


}
