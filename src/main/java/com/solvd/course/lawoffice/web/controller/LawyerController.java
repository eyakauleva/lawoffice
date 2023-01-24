package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.Lawyer;
import com.solvd.course.lawoffice.service.LawyerService;
import com.solvd.course.lawoffice.web.dto.LawyerDto;
import com.solvd.course.lawoffice.web.mapper.LawyerMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/lawyers")
@RequiredArgsConstructor
@Tag(name = "Lawyer", description = "Methods to interact with lawyers")
public class LawyerController {

    private final LawyerService lawyerService;
    private final LawyerMapper lawyerMapper;

    @GetMapping(value = "/{id}")
    @Operation(summary = "Find lawyer by id")
    public LawyerDto findById(@PathVariable("id") @Parameter(description = "Id of the lawyer to find") Long id) {
        Lawyer lawyer = lawyerService.findById(id);
        return lawyerMapper.domainToDto(lawyer);
    }

    @GetMapping
    @Operation(summary = "Find all lawyers")
    public List<LawyerDto> findAll() {
        List<Lawyer> lawyers = lawyerService.findAll();
        return lawyerMapper.domainToDto(lawyers);
    }

}
