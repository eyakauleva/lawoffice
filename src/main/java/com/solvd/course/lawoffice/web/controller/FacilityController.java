package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.Facility;
import com.solvd.course.lawoffice.service.FacilityService;
import com.solvd.course.lawoffice.web.dto.FacilityDto;
import com.solvd.course.lawoffice.web.mapper.FacilityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/facilities")
@RequiredArgsConstructor
public class FacilityController {

    private final FacilityService facilityService;
    private final FacilityMapper facilityMapper;

    @GetMapping("/{id}")
    public FacilityDto findById(@PathVariable("id") Long id) {
        Facility facility = facilityService.findById(id);
        return facilityMapper.domainToDto(facility);
    }

    @GetMapping
    public List<FacilityDto> findAll() {
        List<Facility> facilities = facilityService.findAll();
        return facilityMapper.domainToDto(facilities);
    }

}
