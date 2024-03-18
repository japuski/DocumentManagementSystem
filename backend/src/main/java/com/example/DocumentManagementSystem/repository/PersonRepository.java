package com.example.DocumentManagementSystem.repository;

import com.example.DocumentManagementSystem.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity,Long> {

}
