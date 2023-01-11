package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.Facility;
import com.solvd.course.lawoffice.service.FacilityService;
import com.solvd.course.lawoffice.web.dto.FacilityDto;
import com.solvd.course.lawoffice.web.mapper.FacilityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facilities")
@RequiredArgsConstructor
public class FacilityController {

    private final FacilityService facilityService;
    private final FacilityMapper facilityMapper;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FacilityDto findById(@PathVariable("id") Long id) {
        Facility facility = facilityService.findById(id);
        return facilityMapper.domainToDto(facility);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FacilityDto> findAll() {
        List<Facility> facilities = facilityService.findAll();
        return facilityMapper.domainToDto(facilities);
    }

}
