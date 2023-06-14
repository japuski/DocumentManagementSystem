package com.example.DocumentManagementSystem.repository;

import com.example.DocumentManagementSystem.entity.SegmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentRepository extends JpaRepository<SegmentEntity, Long> {
}
