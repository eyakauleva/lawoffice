package com.solvd.course.lawoffice.web.mapper.criteria;

import com.solvd.course.lawoffice.domain.affair.AffairStatus;
import com.solvd.course.lawoffice.domain.criteria.AffairCriteria;
import com.solvd.course.lawoffice.web.dto.criteria.AffairCriteriaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AffairCriteriaMapper {

    @Mapping(source = "status", target = "status", qualifiedByName = "statusToUpperCase")
    AffairCriteria dtoToDomain(AffairCriteriaDto affairCriteriaDto);

    @Named("statusToUpperCase")
    default AffairStatus statusToUpperCase(String status) {
        return AffairStatus.valueOf(status.toUpperCase());
    }

}
