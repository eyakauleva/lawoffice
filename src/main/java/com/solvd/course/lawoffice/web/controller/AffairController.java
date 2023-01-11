package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.criteria.AffairCriteria;
import com.solvd.course.lawoffice.service.AffairService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/affairs")
@RequiredArgsConstructor
public class AffairController {

    private final AffairService affairService;

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public Integer countByCriteria(AffairCriteria criteria) {
        return affairService.countByCriteria(criteria);
    }

}
