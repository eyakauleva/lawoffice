package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.enums.AffairStatus;
import com.solvd.course.lawoffice.service.AffairService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/affairs")
@RequiredArgsConstructor
public class AffairController {
    private final AffairService affairService;

    @GetMapping("/count")
    public ResponseEntity<Integer> countByStatus(@RequestParam(name = "status") AffairStatus status){
        Integer result = affairService.countByStatus(status);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
