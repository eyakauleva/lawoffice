package com.solvd.course.lawoffice.web.mapper;

import com.solvd.course.lawoffice.domain.LServ;
import com.solvd.course.lawoffice.web.dto.LServDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LServMapper {

    LServDto domainToDto(LServ LServ);

    List<LServDto> domainToDto(List<LServ> services);

    LServ dtoToDomain(LServDto LServDto);

}
