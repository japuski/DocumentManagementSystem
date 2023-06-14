package com.example.DocumentManagementSystem.mapper;

import com.example.DocumentManagementSystem.dto.AddressDto;
import com.example.DocumentManagementSystem.dto.PersonDto;
import com.example.DocumentManagementSystem.entity.AddressEntity;
import com.example.DocumentManagementSystem.entity.PersonEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public PersonEntity mapPersonDtoToPersonEntity(PersonDto personDto){

        PersonEntity personEntity = new PersonEntity();
        personEntity.setName(personDto.getName());
        personEntity.setNip(personDto.getNip());
        personEntity.setAddress(new AddressMapper().mapAddressDtoToAddressEntity(personDto.getAddress()));

        return personEntity;

    }

}
