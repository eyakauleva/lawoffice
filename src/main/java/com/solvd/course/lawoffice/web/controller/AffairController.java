package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.criteria.AffairCriteria;
import com.solvd.course.lawoffice.service.AffairService;
import com.solvd.course.lawoffice.web.dto.criteria.AffairCriteriaDto;
import com.solvd.course.lawoffice.web.mapper.criteria.AffairCriteriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/affairs")
@RequiredArgsConstructor
public class AffairController {

    private final AffairService affairService;
    private final AffairCriteriaMapper affairCriteriaMapper;

    @GetMapping("/count")
    public Integer countByCriteria(AffairCriteriaDto criteriaDto) {
        AffairCriteria criteria = affairCriteriaMapper.dtoToDomain(criteriaDto);
        return affairService.countByCriteria(criteria);
    }

}
