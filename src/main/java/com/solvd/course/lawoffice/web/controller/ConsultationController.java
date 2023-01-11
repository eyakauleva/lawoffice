package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.consultation.Consultation;
import com.solvd.course.lawoffice.domain.criteria.ConsultationCriteria;
import com.solvd.course.lawoffice.service.ConsultationService;
import com.solvd.course.lawoffice.web.dto.ConsultationDto;
import com.solvd.course.lawoffice.web.mapper.ConsultationMapper;
import com.solvd.course.lawoffice.web.validation.ComplexTypeGroup;
import com.solvd.course.lawoffice.web.validation.CreateGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/consultations")
@RequiredArgsConstructor
@Validated
public class ConsultationController {

    private final ConsultationService consultationService;
    private final ConsultationMapper consultationMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ConsultationDto create(@RequestBody @Validated({CreateGroup.class, ComplexTypeGroup.class}) ConsultationDto consultationDto) {
        Consultation consultation = consultationMapper.dtoToDomain(consultationDto);
        consultation = consultationService.create(consultation);
        return consultationMapper.domainToDto(consultation);
    }

    @PatchMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ConsultationDto update(@RequestBody @Validated(ComplexTypeGroup.class) ConsultationDto consultationDto, @PathVariable("id") Long id) {
        consultationDto.setId(id);
        Consultation consultation = consultationMapper.dtoToDomain(consultationDto);
        consultation = consultationService.update(consultation);
        return consultationMapper.domainToDto(consultation);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ConsultationDto> findAll(ConsultationCriteria criteria) {
        List<Consultation> consultations = consultationService.findAll(criteria);
        return consultationMapper.domainToDto(consultations);
    }

}
