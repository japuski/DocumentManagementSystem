package com.example.DocumentManagementSystem.controller;

import com.example.DocumentManagementSystem.dto.PersonDto;
import com.example.DocumentManagementSystem.dto.SegmentDto;
import com.example.DocumentManagementSystem.service.PersonService;
import com.example.DocumentManagementSystem.service.SegmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/segment")
@RequiredArgsConstructor
public class SegmentController {

    private final SegmentService segmentService;

//    @PostMapping("/addSegment")
//    public void addSegment(@RequestBody SegmentDto segmentDto) {
//        segmentService.addSegment(segmentDto);
//    }

}
