package com.example.DocumentManagementSystem.service.invoiceGenerator;

import com.example.DocumentManagementSystem.dto.InvoiceDto;
import com.example.DocumentManagementSystem.dto.PersonDto;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class InvoiceDataLoader {

    public Map<String, Object> getInvoiceData(InvoiceDto invoiceDto){

        Map<String, Object> parameters = new HashMap<>();
        loadHeaderInvoiceData(invoiceDto, parameters);
        loadInvoiceSegments(invoiceDto, parameters);
        loadPersonData(invoiceDto.getSeller(), PersonType.SELLER, parameters);
        loadPersonData(invoiceDto.getBuyer(), PersonType.BUYER, parameters);
        loadSummaryData(invoiceDto, parameters);
        loadVatDetails(invoiceDto, parameters);

        return  parameters;
    }

    private void loadHeaderInvoiceData(InvoiceDto invoiceDto, Map<String, Object> parameters){

        parameters.put("invoiceNumber",invoiceDto.getInvoiceNumber());
        parameters.put("invoiceType", invoiceDto.getInvoiceType());
        parameters.put("dateOfSale", invoiceDto.getDateOfSale());
        parameters.put("dateOfIssue", invoiceDto.getDateOfIssue());
        parameters.put("currency",invoiceDto.getCurrency());
        parameters.put("paymentDeadline",invoiceDto.getPaymentDeadline());
        parameters.put("paymentMethod",invoiceDto.getPaymentMethod());
        parameters.put("accountNumber",invoiceDto.getAccountNumber());
        parameters.put("bank",invoiceDto.getBank());
        parameters.put("transferComment",invoiceDto.getTransferComment());
        parameters.put("amountPaid",invoiceDto.getAmountPaid());

    }

    private void loadInvoiceSegments(InvoiceDto invoiceDto, Map<String, Object> parameters){

        List<InvoiceSegmentModel> segmentsList = new ArrayList<>(
                invoiceDto.getSegments()
                        .stream()
                        .map(segmentDto -> new InvoiceSegmentModelMapper()
                                .mapSegmentDtoToInvoiceSegmentModel(invoiceDto.getSegments()
                                        .indexOf(segmentDto), segmentDto))
                        .toList());

        JRBeanCollectionDataSource segmentsDataSource = new JRBeanCollectionDataSource(segmentsList);

        parameters.put("segmentsDataSet",segmentsDataSource);

    }

    private void loadPersonData(PersonDto personDto, PersonType personTypeEnum, Map<String, Object> parameters){

        String personType = personTypeEnum.getPersonType();

        parameters.put(personType + "Name", personDto.getName());
        parameters.put(personType + "Nip", personDto.getNip());
        parameters.put(personType + "Street", personDto.getAddress().getStreet());
        parameters.put(personType + "StreetNumber", personDto.getAddress().getNumber());
        parameters.put(personType + "PostalCode", personDto.getAddress().getPostalCode());
        parameters.put(personType + "City", personDto.getAddress().getCity());

    }

    private void loadSummaryData(InvoiceDto invoiceDto, Map<String, Object> parameters) {

        parameters.put("netValueSum", invoiceDto.getNetSum());
        parameters.put("vatValueSum", invoiceDto.getVatSum());
        parameters.put("grossValueSum", invoiceDto.getGrossSum());
    }

    private void loadVatDetails(InvoiceDto invoiceDto, Map<String, Object> parameters) {

        List<VatDetailModel> vatDetailModelList = getVatValuesByRates(invoiceDto);
        JRBeanCollectionDataSource vatDetailDataSource = new JRBeanCollectionDataSource(vatDetailModelList);
        parameters.put("vatDetailsDataSet", vatDetailDataSource);

    }

    private List<VatDetailModel> getVatValuesByRates (InvoiceDto invoiceDto){

        List<VatDetailModel> temporaryVatDetailModelList = new ArrayList<>();
        Arrays.stream(VatRate.values()).toList()
                .forEach(vatRate -> temporaryVatDetailModelList
                        .add(VatDetailModel.getVatDetailModel(invoiceDto, vatRate))
                );

        return temporaryVatDetailModelList.stream()
                .filter(vatDetail -> vatDetail.grossValue.compareTo(BigDecimal.ZERO) > 0)
                .collect(Collectors.toList());
    }
}
