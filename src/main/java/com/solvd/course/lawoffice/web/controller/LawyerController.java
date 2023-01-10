package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.Lawyer;
import com.solvd.course.lawoffice.service.LawyerService;
import com.solvd.course.lawoffice.web.dto.LawyerDto;
import com.solvd.course.lawoffice.web.mapper.LawyerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/lawyers")
@RequiredArgsConstructor
public class LawyerController {
    private final LawyerService lawyerService;
    private final LawyerMapper lawyerMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LawyerDto> findAll() {
        List<Lawyer> lawyers = lawyerService.findAll();
        return lawyers.stream()
                .map(lawyerMapper::domainToDto)
                .collect(Collectors.toList());
    }
}
