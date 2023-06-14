package com.example.DocumentManagementSystem.controller;

import com.example.DocumentManagementSystem.dto.AddressDto;
import com.example.DocumentManagementSystem.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/addAddress")
    public void addAddress(@RequestBody AddressDto addressDto) {
        addressService.addAddress(addressDto);
    }

}
