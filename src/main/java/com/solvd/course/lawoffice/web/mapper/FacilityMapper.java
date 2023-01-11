package com.solvd.course.lawoffice.web.mapper;

import com.solvd.course.lawoffice.domain.Facility;
import com.solvd.course.lawoffice.web.dto.FacilityDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacilityMapper {

    FacilityDto domainToDto(Facility facility);

    List<FacilityDto> domainToDto(List<Facility> services);

    Facility dtoToDomain(FacilityDto facilityDto);

}
