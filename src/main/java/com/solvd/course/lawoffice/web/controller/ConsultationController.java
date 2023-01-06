package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.Consultation;
import com.solvd.course.lawoffice.service.ConsultationService;
import com.solvd.course.lawoffice.web.dto.ConsultationDto;
import com.solvd.course.lawoffice.web.mapper.ConsultationMapper;
import com.solvd.course.lawoffice.web.validation.CreateGroup;
import com.solvd.course.lawoffice.web.validation.UpdateGroup;
import jakarta.validation.Valid;
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
    @Validated(CreateGroup.class)
    public ResponseEntity<Void> create(@RequestBody @Valid ConsultationDto consultationDto) {
        Consultation consultation = consultationMapper.dtoToDomain(consultationDto);
        consultationService.create(consultation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    @Validated(UpdateGroup.class)
    public ResponseEntity<Void> update(@RequestBody @Valid ConsultationDto consultationDto, @PathVariable("id") Long id) {
        consultationDto.setId(id);
        Consultation consultation = consultationMapper.dtoToDomain(consultationDto);
        consultationService.update(consultation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ConsultationDto>> getAll(@RequestParam(required = false) boolean unoccupiedOnly) {
        List<Consultation> consultations = consultationService.getAll(unoccupiedOnly);
        List<ConsultationDto> consultationDtos = consultations.stream()
                .map(consultationMapper::domainToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(consultationDtos, HttpStatus.OK);
    }
}
