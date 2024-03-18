package com.example.DocumentManagementSystem.repository;

import com.example.DocumentManagementSystem.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    AddressEntity findByStreet(String street);

}
