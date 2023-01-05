package com.solvd.course.lawoffice.web.mapper;

import com.solvd.course.lawoffice.domain.Service;
import com.solvd.course.lawoffice.web.dto.ServiceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    ServiceDto domainToDto(Service service);
    Service dtoToDomain(ServiceDto serviceDto);
}
