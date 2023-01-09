package com.solvd.course.lawoffice.web.mapper;

import com.solvd.course.lawoffice.domain.LServ;
import com.solvd.course.lawoffice.web.dto.LServDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LServMapper {
    LServDto domainToDto(LServ LServ);
    LServ dtoToDomain(LServDto LServDto);
}
