package com.solvd.course.lawoffice.web.mapper.criteria;

import com.solvd.course.lawoffice.domain.criteria.ConsultationCriteria;
import com.solvd.course.lawoffice.web.dto.criteria.ConsultationCriteriaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsultationCriteriaMapper {

    ConsultationCriteria dtoToDomain(ConsultationCriteriaDto consultationCriteriaDto);

}
