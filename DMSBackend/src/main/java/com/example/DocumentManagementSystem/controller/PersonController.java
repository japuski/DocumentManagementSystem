package com.example.DocumentManagementSystem.controller;

import com.example.DocumentManagementSystem.dto.AddressDto;
import com.example.DocumentManagementSystem.dto.PersonDto;
import com.example.DocumentManagementSystem.service.AddressService;
import com.example.DocumentManagementSystem.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping("/addPerson")
    public void addAddress(@RequestBody PersonDto personDto) {
        personService.addPerson(personDto);
    }


}
