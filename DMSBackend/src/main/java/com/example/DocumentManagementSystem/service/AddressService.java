package com.example.DocumentManagementSystem.service;

import com.example.DocumentManagementSystem.dto.AddressDto;
import com.example.DocumentManagementSystem.mapper.AddressMapper;
import com.example.DocumentManagementSystem.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public void addAddress(AddressDto addressDto){
        addressRepository.save(addressMapper.mapAddressDtoToAddressEntity(addressDto));
    }

}
