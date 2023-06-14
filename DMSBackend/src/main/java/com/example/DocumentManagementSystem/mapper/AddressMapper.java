package com.example.DocumentManagementSystem.mapper;

import com.example.DocumentManagementSystem.dto.AddressDto;
import com.example.DocumentManagementSystem.entity.AddressEntity;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressEntity mapAddressDtoToAddressEntity(AddressDto addressDto){

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet(addressDto.getStreet());
        addressEntity.setNumber(addressDto.getNumber());
        addressEntity.setPostalCode(addressDto.getPostalCode());
        addressEntity.setCity(addressDto.getCity());

        return addressEntity;

    }

}
