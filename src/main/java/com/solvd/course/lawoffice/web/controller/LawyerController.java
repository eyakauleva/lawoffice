package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.Lawyer;
import com.solvd.course.lawoffice.service.LawyerService;
import com.solvd.course.lawoffice.web.dto.LawyerDto;
import com.solvd.course.lawoffice.web.mapper.LawyerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/lawyers")
@RequiredArgsConstructor
public class LawyerController {

    private final LawyerService lawyerService;
    private final LawyerMapper lawyerMapper;

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LawyerDto findById(@PathVariable("id") Long id) {
        Lawyer lawyer = lawyerService.findById(id);
        return lawyerMapper.domainToDto(lawyer);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LawyerDto> findAll() {
        List<Lawyer> lawyers = lawyerService.findAll();
        return lawyerMapper.domainToDto(lawyers);
    }

}
