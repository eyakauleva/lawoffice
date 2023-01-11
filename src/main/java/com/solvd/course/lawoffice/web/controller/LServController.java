package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.LServ;
import com.solvd.course.lawoffice.service.LServService;
import com.solvd.course.lawoffice.web.dto.LServDto;
import com.solvd.course.lawoffice.web.mapper.LServMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class LServController {

    private final LServService LServService;
    private final LServMapper LServMapper;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LServDto findById(@PathVariable("id") Long id) {
        LServ service = LServService.findById(id);
        return LServMapper.domainToDto(service);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LServDto> findAll() {
        List<LServ> services = LServService.findAll();
        return LServMapper.domainToDto(services);
    }

}
