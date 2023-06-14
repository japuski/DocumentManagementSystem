package com.example.DocumentManagementSystem.controller;

import com.example.DocumentManagementSystem.dto.ProductDto;
import com.example.DocumentManagementSystem.dto.SegmentDto;
import com.example.DocumentManagementSystem.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/addProduct")
    public void addSegment(@RequestBody ProductDto productDto) {
        productService.addProduct(productDto);
    }

}
