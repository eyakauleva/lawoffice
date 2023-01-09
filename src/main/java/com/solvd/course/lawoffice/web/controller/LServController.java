package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.LServ;
import com.solvd.course.lawoffice.service.LServService;
import com.solvd.course.lawoffice.web.dto.LServDto;
import com.solvd.course.lawoffice.web.mapper.LServMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class LServController {
    private final LServService LServService;
    private final LServMapper LServMapper;

    @GetMapping("/{id}")
    public ResponseEntity<LServDto> findById(@PathVariable("id") Long id) {
        LServ LServ = LServService.findById(id);
        LServDto LServDto = LServMapper.domainToDto(LServ);
        return new ResponseEntity<>(LServDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<LServDto>> findAll() {
        List<LServ> LServs = LServService.findAll();
        List<LServDto> LServDtos = LServs.stream()
                .map(LServMapper::domainToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(LServDtos, HttpStatus.OK);
    }
}
