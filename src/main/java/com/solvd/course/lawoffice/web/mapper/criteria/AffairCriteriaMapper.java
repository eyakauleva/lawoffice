package com.solvd.course.lawoffice.web.mapper.criteria;

import com.solvd.course.lawoffice.domain.criteria.AffairCriteria;
import com.solvd.course.lawoffice.web.dto.criteria.AffairCriteriaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AffairCriteriaMapper {

    AffairCriteria dtoToDomain(AffairCriteriaDto affairCriteriaDto);

}
