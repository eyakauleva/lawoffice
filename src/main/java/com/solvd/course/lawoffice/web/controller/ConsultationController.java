package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.Consultation;
import com.solvd.course.lawoffice.service.ConsultationService;
import com.solvd.course.lawoffice.web.dto.ConsultationDto;
import com.solvd.course.lawoffice.web.mapper.ConsultationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/consultations")
public class ConsultationController {
    private final ConsultationService consultationService;
    private final ConsultationMapper consultationMapper;

    @Autowired
    public ConsultationController(ConsultationService consultationService, ConsultationMapper consultationMapper) {
        this.consultationService = consultationService;
        this.consultationMapper = consultationMapper;
    }

    @PostMapping
    public ResponseEntity<Void> createConsultation(@RequestBody ConsultationDto consultationDto) {
        Consultation consultation = consultationMapper.dtoToDomain(consultationDto);
        consultationService.createConsultation(consultation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> assignToConsultation(@RequestBody ConsultationDto consultationDto, @PathVariable("id") Long id) {
        consultationDto.setId(id);
        Consultation consultation = consultationMapper.dtoToDomain(consultationDto);
        consultationService.assignToConsultation(consultation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ConsultationDto>> getConsultations(@RequestParam(required = false) boolean unoccupiedOnly) {
        List<Consultation> consultations = consultationService.getConsultations(unoccupiedOnly);
        List<ConsultationDto> consultationDtos = consultations.stream().map(consultationMapper::domainToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(consultationDtos, HttpStatus.OK);
    }
}
