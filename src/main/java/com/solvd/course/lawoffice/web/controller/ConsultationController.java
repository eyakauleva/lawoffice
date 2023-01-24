package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.consultation.Consultation;
import com.solvd.course.lawoffice.domain.criteria.ConsultationCriteria;
import com.solvd.course.lawoffice.service.ConsultationService;
import com.solvd.course.lawoffice.web.dto.ConsultationDto;
import com.solvd.course.lawoffice.web.dto.criteria.ConsultationCriteriaDto;
import com.solvd.course.lawoffice.web.mapper.ConsultationMapper;
import com.solvd.course.lawoffice.web.mapper.criteria.ConsultationCriteriaMapper;
import com.solvd.course.lawoffice.web.validation.CreateGroup;
import com.solvd.course.lawoffice.web.validation.LawyerIdRequiredGroup;
import com.solvd.course.lawoffice.web.validation.LawyerUserIdRequiredGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/consultations")
@RequiredArgsConstructor
@Validated
@Tag(name = "Consultation", description = "Methods to interact with consultations")
public class ConsultationController {

    private final ConsultationService consultationService;
    private final ConsultationMapper consultationMapper;
    private final ConsultationCriteriaMapper consultationCriteriaMapper;

    @PostMapping
    @PreAuthorize("isConsultationCreator(#consultationDto)")
    @Operation(summary = "Create new consultation")
    public ConsultationDto create(@RequestBody @Validated({CreateGroup.class, LawyerIdRequiredGroup.class, LawyerUserIdRequiredGroup.class})
                                  @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Consultation to create") ConsultationDto consultationDto) {
        Consultation consultation = consultationMapper.dtoToDomain(consultationDto);
        consultation = consultationService.create(consultation);
        return consultationMapper.domainToDto(consultation);
    }

    @PatchMapping(value = "/{id}")
    @PostAuthorize("isConsultationRelated()")
    @Operation(summary = "Update existing consultation")
    public ConsultationDto update(@RequestBody @Validated({LawyerIdRequiredGroup.class}) @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Consultation fields to update") ConsultationDto consultationDto,
                                  @PathVariable("id") @Parameter(description = "Id of the consultation to update") Long id) {
        consultationDto.setId(id);
        Consultation consultation = consultationMapper.dtoToDomain(consultationDto);
        consultation = consultationService.update(consultation);
        return consultationMapper.domainToDto(consultation);
    }

    @GetMapping
    @Operation(summary = "Find consultations by criteria")
    public List<ConsultationDto> findByCriteria(@Parameter(description = "Criteria to find consultations by") ConsultationCriteriaDto criteriaDto) {
        ConsultationCriteria criteria = consultationCriteriaMapper.dtoToDomain(criteriaDto);
        List<Consultation> consultations = consultationService.findByCriteria(criteria);
        return consultationMapper.domainToDto(consultations);
    }

}
