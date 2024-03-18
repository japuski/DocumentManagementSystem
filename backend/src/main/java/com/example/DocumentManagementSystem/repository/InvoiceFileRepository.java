package com.example.DocumentManagementSystem.repository;

import com.example.DocumentManagementSystem.entity.InvoiceFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceFileRepository extends JpaRepository<InvoiceFileEntity, Long> {
}
