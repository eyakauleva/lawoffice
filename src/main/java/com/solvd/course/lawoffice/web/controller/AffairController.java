package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.service.AffairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/affairs")
public class AffairController {
    private final AffairService affairService;

    @Autowired
    public AffairController(AffairService affairService) {
        this.affairService = affairService;
    }

    @GetMapping("/success")
    public ResponseEntity<Integer> countSuccessAffairs(){
        Integer result = affairService.countSuccessAffairs();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/failure")
    public ResponseEntity<Integer> countFailureAffairs(){
        Integer result = affairService.countFailureAffairs();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
