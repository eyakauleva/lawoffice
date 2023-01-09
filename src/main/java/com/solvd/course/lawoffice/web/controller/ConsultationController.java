package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.Consultation;
import com.solvd.course.lawoffice.domain.exception.ValidationException;
import com.solvd.course.lawoffice.service.ConsultationService;
import com.solvd.course.lawoffice.web.dto.ConsultationDto;
import com.solvd.course.lawoffice.web.mapper.ConsultationMapper;
import com.solvd.course.lawoffice.web.validation.CreateGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/consultations")
@RequiredArgsConstructor
@Validated
public class ConsultationController {
    private final ConsultationService consultationService;
    private final ConsultationMapper consultationMapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Validated(CreateGroup.class) ConsultationDto consultationDto) {
        if (Objects.isNull(consultationDto.getLawyer().getId()))
            throw new ValidationException("Consultation must contain a lawyer's id");
        if (Objects.nonNull(consultationDto.getUser()) && Objects.isNull(consultationDto.getUser().getId()))
            throw new ValidationException("Consultation must contain a user's id");
        Consultation consultation = consultationMapper.dtoToDomain(consultationDto);
        consultationService.create(consultation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody ConsultationDto consultationDto, @PathVariable("id") Long id) {
        if (Objects.nonNull(consultationDto.getLawyer()) && Objects.isNull(consultationDto.getLawyer().getId()))
            throw new ValidationException("Consultation must contain a lawyer's id");
        if (Objects.nonNull(consultationDto.getUser()) && Objects.isNull(consultationDto.getUser().getId()))
            throw new ValidationException("Consultation must contain a user's id");
        consultationDto.setId(id);
        Consultation consultation = consultationMapper.dtoToDomain(consultationDto);
        consultationService.update(consultation);
        return new ResponseEntity<>(HttpStatus.OK);
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
