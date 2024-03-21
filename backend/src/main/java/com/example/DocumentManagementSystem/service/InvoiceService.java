package com.example.DocumentManagementSystem.service;

import com.example.DocumentManagementSystem.dto.InvoiceDto;
import com.example.DocumentManagementSystem.entity.*;
import com.example.DocumentManagementSystem.mapper.*;
import com.example.DocumentManagementSystem.repository.*;
import com.example.DocumentManagementSystem.service.invoiceGenerator.InvoiceGeneratorJasper;
import com.example.DocumentManagementSystem.service.invoiceGenerator.InvoiceScheduleType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceFileRepository invoiceFileRepository;
    private final InvoiceMapper invoiceMapper;
    private final InvoiceGeneratorJasper invoiceGenerator;

    public Page<InvoiceDto> getInvoices(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("bank").ascending());
        InvoiceMapper invoiceMapper = new InvoiceMapper();
        Page<InvoiceEntity> invoiceEntityPage = invoiceRepository.findAll(pageable);
//        Page<InvoiceEntity> invoiceEntityPage = invoiceRepository.findAllWithFilters("ING", pageable);
        System.out.println(invoiceEntityPage.getContent().get(0).getBank());
        List<InvoiceEntity> invoiceEntityList = invoiceEntityPage.getContent();
        List<InvoiceDto> invoiceDtos = invoiceEntityList.stream()
                .map(invoiceEntity -> invoiceMapper.mapInvoiceEntityToInvoiceDtoTo(invoiceEntity))
                .collect(Collectors.toList());
        return new PageImpl<>(invoiceDtos, pageable, invoiceEntityPage.getTotalElements());
    }

    @Transactional
    public void addInvoice(InvoiceDto invoiceDto) throws IOException, JRException {

        InvoiceEntity invoiceEntity = invoiceMapper.mapInvoiceDtoToInvoiceEntity(invoiceDto);

        InvoiceFileEntity invoiceFileEntity = new InvoiceFileEntity();
        invoiceFileEntity.setFilename("FV_" + invoiceDto.getInvoiceNumber());
        byte[] file = invoiceGenerator.generate(invoiceDto, InvoiceScheduleType.SCHEDULE3);
        invoiceFileEntity.setFile(file);
        invoiceEntity.setFile(invoiceFileEntity);

        invoiceRepository.save(invoiceEntity);
    }

    public InvoiceEntity getInvoiceEntity(long invoiceId){

        InvoiceEntity invoice = invoiceRepository.findById(invoiceId).get();
        return invoice;
    }

    public InvoiceDto getInvoiceDto(long invoiceId){

        InvoiceDto invoice = invoiceMapper.mapInvoiceEntityToInvoiceDtoTo(invoiceRepository.findById(invoiceId).get());
        return invoice;
    }

    public List<InvoiceDto> getInvoiceDtoList(){

        List<InvoiceDto> invoiceDtoList = new ArrayList<>();
        List<InvoiceEntity> invoiceEntityList = invoiceRepository.findAll();
        invoiceEntityList.forEach(invoiceEntity -> invoiceDtoList.add(new InvoiceMapper().mapInvoiceEntityToInvoiceDtoTo(invoiceEntity)));

        return invoiceDtoList;
    }

    public long[] getInvoicesByNumber(){
        List<InvoiceEntity> invoiceEntityList = invoiceRepository.findAll();
        long[] invoiceNumbers = invoiceEntityList.stream().mapToLong(invoiceEntity -> invoiceEntity.getId()).toArray();
        return invoiceNumbers;
    }

    public void saveFile(byte[] file, InvoiceDto invoiceDto){
        InvoiceFileEntity invoiceFileEntity = new InvoiceFileEntity();
        invoiceFileEntity.setFilename(String.valueOf("FV" + invoiceDto.getInvoiceNumber() + ".docx"));
        invoiceFileEntity.setFile(file);
        invoiceFileRepository.save(invoiceFileEntity);
    }

    public ResponseEntity<byte[]> getFile(long invoiceId) {
        InvoiceEntity invoiceEntity = invoiceRepository.findById(invoiceId).get();
        InvoiceFileEntity invoiceFileEntity = invoiceFileRepository.findById(invoiceEntity.getFile().getId()).get();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + invoiceFileEntity.getFilename());
        return ResponseEntity.ok().headers(headers).body(invoiceFileEntity.getFile());
    }

}
