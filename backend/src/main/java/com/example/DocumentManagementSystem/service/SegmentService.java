package com.example.DocumentManagementSystem.service;

import com.example.DocumentManagementSystem.dto.SegmentDto;
import com.example.DocumentManagementSystem.mapper.SegmentMapper;
import com.example.DocumentManagementSystem.repository.SegmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SegmentService {

    private final SegmentRepository segmentRepository;
    private final SegmentMapper segmentMapper;

//    public void addSegment(SegmentDto segmentDto){
//        segmentRepository.save(segmentMapper.mapSegmentDtoToSegmentEntity(segmentDto,));
//    }


}
