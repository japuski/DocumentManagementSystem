package com.example.DocumentManagementSystem.repository;

import com.example.DocumentManagementSystem.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity,Long> {
}
