package com.example.DocumentManagementSystem.service.invoiceGenerator;

import com.example.DocumentManagementSystem.dto.InvoiceDto;
import com.example.DocumentManagementSystem.dto.PersonDto;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.jfree.util.Log;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
@NoArgsConstructor
public class InvoiceGeneratorJasper {

    private InvoiceDto invoiceDto; //final?
    private InputStream scheduleFile; //final?
    private String resultFilePath;
    private Map<String,Object> parameters = new HashMap<>();


    //obsłużyć wyjatek jak nie ma takiego szablonu
    public byte[] generate(InvoiceDto invoiceDto, InvoiceScheduleType invoiceSchedule) throws JRException, IOException {

        this.invoiceDto = invoiceDto;
        this.resultFilePath = String.valueOf(System.currentTimeMillis());

        if (invoiceSchedule == InvoiceScheduleType.SCHEDULE1) {
            this.scheduleFile = new FileInputStream("src/main/resources/templates/inovice_s1.jrxml");
        } else if (invoiceSchedule == InvoiceScheduleType.SCHEDULE2){
            this.scheduleFile = new FileInputStream("src/main/resources/templates/inovice_s2.jrxml");
        }

        loadHeaderInvoiceData();
        loadInvoiceSegments();
        loadPersonData(invoiceDto.getSeller(), PersonType.SELLER);  //jakoś lepiej - w Invoice mam już podział na seller i buyer
        loadPersonData(invoiceDto.getBuyer(), PersonType.BUYER);
        loadSummaryData();
        loadVatDetails();

        JasperReport invoice = JasperCompileManager.compileReport(this.scheduleFile);
        JasperPrint print = JasperFillManager.fillReport(invoice,parameters, new JREmptyDataSource());
        JasperExportManager.exportReportToPdfFile(print,resultFilePath);

        return fileOutputStreamToByteArray(resultFilePath);
    }

    private void loadHeaderInvoiceData(){

        this.parameters.put("invoiceNumber",this.invoiceDto.getInvoiceNumber());
        this.parameters.put("invoiceType", this.invoiceDto.getInvoiceType());
        this.parameters.put("dateOfSale", this.invoiceDto.getDateOfSale());
        this.parameters.put("dateOfIssue", this.invoiceDto.getDateOfIssue());
        this.parameters.put("currency",this.invoiceDto.getCurrency());
        this.parameters.put("paymentDeadline",this.invoiceDto.getPaymentDeadline());
        this.parameters.put("paymentMethod",this.invoiceDto.getPaymentMethod());
        this.parameters.put("accountNumber",this.invoiceDto.getAccountNumber());
        this.parameters.put("bank",this.invoiceDto.getBank());
        this.parameters.put("transferComment",this.invoiceDto.getTransferComment());
        this.parameters.put("amountPaid",this.invoiceDto.getAmountPaid());

        log.info("PAYMENT DEADLINE: " + invoiceDto.getPaymentDeadline().toString());
    }

    private void loadInvoiceSegments(){

        List<InvoiceSegmentModel> segmentsList = new ArrayList<>(
                invoiceDto.getSegments()
                        .stream()
                        .map(segmentDto -> new InvoiceSegmentModelMapper().mapSegmentDtoToInvoiceSegmentModel(invoiceDto.getSegments().indexOf(segmentDto), segmentDto))
                        .toList());

        JRBeanCollectionDataSource segmentsDataSource = new JRBeanCollectionDataSource(segmentsList);

        this.parameters.put("segmentsDataSet",segmentsDataSource);

    }

    private void loadPersonData(PersonDto personDto, PersonType personTypeEnum){

        String personType = "";

        if (personTypeEnum == PersonType.BUYER) {
            personType = "buyer";
        } else {
            personType = "seller";
        }

        this.parameters.put(personType + "Name", personDto.getName());
        this.parameters.put(personType + "Nip", personDto.getNip());
        this.parameters.put(personType + "Street", personDto.getAddress().getStreet());
        this.parameters.put(personType + "StreetNumber", personDto.getAddress().getNumber());
        this.parameters.put(personType + "PostalCode", personDto.getAddress().getPostalCode());
        this.parameters.put(personType + "City", personDto.getAddress().getCity());

    }

    private void loadSummaryData() {

        this.parameters.put("netValueSum", invoiceDto.getNetSum());
        this.parameters.put("vatValueSum", invoiceDto.getVatSum());
        this.parameters.put("grossValueSum", invoiceDto.getGrossSum());
    }

    private void loadVatDetails() {

        List<VatDetailModel> vatDetailModelList = new ArrayList<>();
        List<VatDetailModel> temporaryVatDetailModelList = new ArrayList<>();

        Arrays.stream(VatRate.values()).toList()
                .forEach(vatRate -> temporaryVatDetailModelList
                        .add(new VatDetailModel(invoiceDto.getNetSumByVatRate(vatRate), vatRate.getRate(), invoiceDto.getVatSumByVatRate(vatRate), invoiceDto.getGrossSumByVatRate(vatRate)))
                );

        vatDetailModelList = temporaryVatDetailModelList.stream().filter(vatDetail -> vatDetail.grossValue.compareTo(BigDecimal.ZERO) > 0).collect(Collectors.toList());

        JRBeanCollectionDataSource vatDetailDataSource = new JRBeanCollectionDataSource(vatDetailModelList);

        this.parameters.put("vatDetailsDataSet", vatDetailDataSource);

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
