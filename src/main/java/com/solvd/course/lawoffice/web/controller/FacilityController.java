package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.Facility;
import com.solvd.course.lawoffice.service.FacilityService;
import com.solvd.course.lawoffice.web.dto.FacilityDto;
import com.solvd.course.lawoffice.web.mapper.FacilityMapper;
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
@RequestMapping("/api/v1/facilities")
@RequiredArgsConstructor
@Tag(name = "Facility", description = "Methods to interact with facilities")
public class FacilityController {

    private final FacilityService facilityService;
    private final FacilityMapper facilityMapper;

    @GetMapping("/{id}")
    @Operation(summary = "Find facility by id")
    public FacilityDto findById(@PathVariable("id") @Parameter(description = "Id of the facility to find") Long id) {
        Facility facility = facilityService.findById(id);
        return facilityMapper.domainToDto(facility);
    }

    @GetMapping
    @Operation(summary = "Find all facilities")
    public List<FacilityDto> findAll() {
        List<Facility> facilities = facilityService.findAll();
        return facilityMapper.domainToDto(facilities);
    }

}
