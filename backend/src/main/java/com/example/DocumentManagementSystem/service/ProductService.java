package com.example.DocumentManagementSystem.service;

import com.example.DocumentManagementSystem.dto.ProductDto;
import com.example.DocumentManagementSystem.mapper.ProductMapper;
import com.example.DocumentManagementSystem.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public void addProduct(ProductDto productDto){
        productRepository.save(productMapper.mapProductDtoToProductEntity(productDto));
    }

}
