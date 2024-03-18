package com.example.DocumentManagementSystem.service;

import com.example.DocumentManagementSystem.dto.AddressDto;
import com.example.DocumentManagementSystem.dto.PersonDto;
import com.example.DocumentManagementSystem.mapper.AddressMapper;
import com.example.DocumentManagementSystem.mapper.PersonMapper;
import com.example.DocumentManagementSystem.repository.AddressRepository;
import com.example.DocumentManagementSystem.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public void addPerson(PersonDto personDto){
        personRepository.save(personMapper.mapPersonDtoToPersonEntity(personDto));
    }
}
