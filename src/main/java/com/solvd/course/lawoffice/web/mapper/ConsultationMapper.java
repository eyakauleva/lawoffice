package com.solvd.course.lawoffice.web.mapper;

import com.solvd.course.lawoffice.domain.consultation.Consultation;
import com.solvd.course.lawoffice.web.dto.ConsultationDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, LawyerMapper.class})
public interface ConsultationMapper {

    ConsultationDto domainToDto(Consultation consultation);

    List<ConsultationDto> domainToDto(List<Consultation> consultations);

    Consultation dtoToDomain(ConsultationDto consultationDto);

}
