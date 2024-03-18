package com.example.DocumentManagementSystem;

import com.example.DocumentManagementSystem.dto.*;
import com.example.DocumentManagementSystem.entity.InvoiceEntity;
import com.example.DocumentManagementSystem.mapper.InvoiceMapper;
import com.example.DocumentManagementSystem.repository.InvoiceRepository;
import com.example.DocumentManagementSystem.service.InvoiceService;
import com.example.DocumentManagementSystem.service.invoiceGenerator.InvoiceGeneratorJasper;
import com.example.DocumentManagementSystem.service.invoiceGenerator.InvoiceScheduleType;
import com.example.DocumentManagementSystem.utils.MockInvoiceDataUtil;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.math3.analysis.function.Add;
import org.hibernate.validator.constraints.ModCheck;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import static com.example.DocumentManagementSystem.utils.MockInvoiceDataUtil.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private InvoiceMapper invoiceMapper;

    @Mock
    private InvoiceGeneratorJasper invoiceGeneratorJasper;

    @InjectMocks
    private InvoiceService invoiceService;

    private static final List<InvoiceEntity> INVOICES = MockInvoiceDataUtil.INVOICES;

    @Test
    void shouldAddInvoice() throws JRException, IOException {
        InvoiceDto invoiceDto = createInvoiceDto();
        InvoiceEntity invoiceEntity = createInvoiceEntity();
        InputStream inputStream = new FileInputStream("src/main/resources/templates/inovice_s1.jrxml");

        when(invoiceMapper.mapInvoiceDtoToInvoiceEntity(invoiceDto)).thenReturn(invoiceEntity);
        when(invoiceGeneratorJasper.generate(invoiceDto, InvoiceScheduleType.SCHEDULE1)).thenReturn(inputStream.readAllBytes());

        invoiceService.addInvoice(invoiceDto);

        verify(invoiceRepository).save(invoiceEntity);

    }








}
