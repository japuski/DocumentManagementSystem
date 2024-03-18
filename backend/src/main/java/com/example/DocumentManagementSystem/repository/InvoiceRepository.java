package com.example.DocumentManagementSystem.repository;

import com.example.DocumentManagementSystem.entity.AddressEntity;
import com.example.DocumentManagementSystem.entity.InvoiceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity,Long> {

    Page<InvoiceEntity> findAllByBank(String bank, Pageable pageable);

    @Query(value = "select i from InvoiceEntity i where :bank is null or i.bank = :bank")
    Page<InvoiceEntity> findAllWithFilters(@Param("bank") String bank, Pageable pageable);

}
