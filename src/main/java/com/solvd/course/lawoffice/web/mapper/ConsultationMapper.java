package com.solvd.course.lawoffice.web.mapper;

import com.solvd.course.lawoffice.domain.Consultation;
import com.solvd.course.lawoffice.web.dto.ConsultationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, LawyerMapper.class})
public interface ConsultationMapper {
    ConsultationDto domainToDto(Consultation consultation);
    Consultation dtoToDomain(ConsultationDto consultationDto);
}
