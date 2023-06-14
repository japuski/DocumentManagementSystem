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
        productEntity.setPkwiu(productDto.getPkwiu());
        productEntity.setUnit(productDto.getUnit());
        productEntity.setNettoPrice(productDto.getNettoPrice());
        productEntity.setTaxRate(productDto.getTaxRate());

        return productEntity;

    }
}
