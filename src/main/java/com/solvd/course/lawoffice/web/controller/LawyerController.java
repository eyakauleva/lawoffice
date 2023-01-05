package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.Lawyer;
import com.solvd.course.lawoffice.service.LawyerService;
import com.solvd.course.lawoffice.web.dto.LawyerDto;
import com.solvd.course.lawoffice.web.mapper.LawyerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/lawyers")
public class LawyerController {
    private final LawyerService lawyerService;
    private final LawyerMapper lawyerMapper;

    @Autowired
    public LawyerController(LawyerService lawyerService, LawyerMapper lawyerMapper) {
        this.lawyerService = lawyerService;
        this.lawyerMapper = lawyerMapper;
    }

    @GetMapping
    public ResponseEntity<List<LawyerDto>> getAllLawyers(){
        List<Lawyer> lawyers = lawyerService.getAllLawyers();
        List<LawyerDto> lawyerDtos = lawyers.stream().map(lawyerMapper::domainToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(lawyerDtos, HttpStatus.OK);
    }
}
