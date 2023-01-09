package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.Consultation;
import com.solvd.course.lawoffice.service.ConsultationService;
import com.solvd.course.lawoffice.web.dto.ConsultationDto;
import com.solvd.course.lawoffice.web.mapper.ConsultationMapper;
import com.solvd.course.lawoffice.web.validation.ComplexTypeGroup;
import com.solvd.course.lawoffice.web.validation.CreateGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/consultations")
@RequiredArgsConstructor
@Validated
public class ConsultationController {
    private final ConsultationService consultationService;
    private final ConsultationMapper consultationMapper;

    @PostMapping
    public ResponseEntity<ConsultationDto> create(@RequestBody @Validated({CreateGroup.class, ComplexTypeGroup.class}) ConsultationDto consultationDto) {
        Consultation consultation = consultationMapper.dtoToDomain(consultationDto);
        consultation = consultationService.create(consultation);
        return new ResponseEntity<>(consultationMapper.domainToDto(consultation), HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<ConsultationDto> update(@RequestBody @Validated(ComplexTypeGroup.class) ConsultationDto consultationDto, @PathVariable("id") Long id) {
        consultationDto.setId(id);
        Consultation consultation = consultationMapper.dtoToDomain(consultationDto);
        consultation = consultationService.update(consultation);
        return new ResponseEntity<>(consultationMapper.domainToDto(consultation), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ConsultationDto>> findAll(@RequestParam(required = false) boolean unoccupiedOnly) {
        List<Consultation> consultations = consultationService.findAll(unoccupiedOnly);
        List<ConsultationDto> consultationDtos = consultations.stream()
                .map(consultationMapper::domainToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(consultationDtos, HttpStatus.OK);
    }
}
