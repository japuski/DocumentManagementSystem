package com.example.DocumentManagementSystem.mapper;

import com.example.DocumentManagementSystem.dto.AddressDto;
import com.example.DocumentManagementSystem.dto.ProductDto;
import com.example.DocumentManagementSystem.entity.AddressEntity;
import com.example.DocumentManagementSystem.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductEntity mapProductDtoToProductEntity(ProductDto productDto){

        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductName(productDto.getProductName());
        productEntity.setUnit(productDto.getUnit());
        productEntity.setNetPrice(productDto.getNetPrice());
        productEntity.setVatRate(productDto.getVatRate());

        return productEntity;

    }

    public ProductDto mapProductEntityToProductDto(ProductEntity productEntity){

        ProductDto productDto = new ProductDto();
        productDto.setProductName(productEntity.getProductName());
        productDto.setUnit(productEntity.getUnit());
        productDto.setNetPrice(productEntity.getNetPrice());
        productDto.setVatRate(productEntity.getVatRate());

        return productDto;

    }
}
