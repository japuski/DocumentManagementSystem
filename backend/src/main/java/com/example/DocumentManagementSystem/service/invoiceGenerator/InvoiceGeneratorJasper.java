package com.example.DocumentManagementSystem.service.invoiceGenerator;

import com.example.DocumentManagementSystem.dto.InvoiceDto;
import com.example.DocumentManagementSystem.dto.PersonDto;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class InvoiceGeneratorJasper {

    private InvoiceDataLoader invoiceDataLoader;

    public InvoiceGeneratorJasper (InvoiceDataLoader invoiceDataLoader){
        this.invoiceDataLoader = invoiceDataLoader;
    }

    public byte[] generate(InvoiceDto invoiceDto, InvoiceScheduleType invoiceSchedule) throws JRException, IOException {

        String resultFilePath = String.valueOf(System.currentTimeMillis());
        InputStream scheduleFile = loadFileTemplate(invoiceSchedule);
        JasperReport invoice = JasperCompileManager.compileReport(scheduleFile);
        JasperPrint print = JasperFillManager.fillReport(invoice,invoiceDataLoader.getInvoiceData(invoiceDto), new JREmptyDataSource());
        JasperExportManager.exportReportToPdfFile(print,resultFilePath);

        return fileOutputStreamToByteArray(resultFilePath);
    }

    private InputStream loadFileTemplate(InvoiceScheduleType invoiceSchedule) throws FileNotFoundException {

        if (invoiceSchedule == InvoiceScheduleType.SCHEDULE1) {
            return new FileInputStream("src/main/resources/templates/inovice_s1.jrxml");

        } else if (invoiceSchedule == InvoiceScheduleType.SCHEDULE2){
            return new FileInputStream("src/main/resources/templates/inovice_s2.jrxml");
        };

        throw new RuntimeException("File Template hasn't found");
    }



    private byte[] fileOutputStreamToByteArray(String filePath) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    // private String numbersToWords(Big Decimal number) { }

}
