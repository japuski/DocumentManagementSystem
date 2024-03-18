package com.example.DocumentManagementSystem.mapper;

import com.example.DocumentManagementSystem.dto.InvoiceDto;
import com.example.DocumentManagementSystem.dto.SegmentDto;
import com.example.DocumentManagementSystem.entity.InvoiceEntity;
import com.example.DocumentManagementSystem.entity.SegmentEntity;
import org.springframework.stereotype.Component;

@Component
public class SegmentMapper {

    public SegmentEntity mapSegmentDtoToSegmentEntity(SegmentDto segmentDto, InvoiceEntity invoiceEntity){

        SegmentEntity segmentEntity = new SegmentEntity();
        segmentEntity.setInvoice(invoiceEntity);
        segmentEntity.setQuantity(segmentDto.getQuantity());
        segmentEntity.setProduct(new ProductMapper().mapProductDtoToProductEntity(segmentDto.getProduct()));

        return segmentEntity;
    }

    public SegmentDto mapSegmentEntityToSegmentDto(SegmentEntity segmentEntity){

        SegmentDto segmentDto = new SegmentDto();
        segmentDto.setQuantity(segmentEntity.getQuantity());
        segmentDto.setProduct(new ProductMapper().mapProductEntityToProductDto(segmentEntity.getProduct()));

        return segmentDto;
    }

}
