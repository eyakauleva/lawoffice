package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.criteria.AffairCriteria;
import com.solvd.course.lawoffice.service.AffairService;
import com.solvd.course.lawoffice.web.dto.criteria.AffairCriteriaDto;
import com.solvd.course.lawoffice.web.mapper.criteria.AffairCriteriaMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/affairs")
@RequiredArgsConstructor
@Tag(name = "Affairs", description = "Methods to interact with affairs")
public class AffairController {

    private final AffairService affairService;
    private final AffairCriteriaMapper affairCriteriaMapper;

    @GetMapping("/count")
    @Operation(summary = "Count affairs by criteria")
    public Integer countByCriteria(@Parameter(description = "Criteria to count affairs by") AffairCriteriaDto criteriaDto) {
        AffairCriteria criteria = affairCriteriaMapper.dtoToDomain(criteriaDto);
        return affairService.countByCriteria(criteria);
    }

}
