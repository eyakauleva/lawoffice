package com.solvd.course.lawoffice.web.mapper;

import com.solvd.course.lawoffice.domain.Lawyer;
import com.solvd.course.lawoffice.web.dto.LawyerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, LServMapper.class})
public interface LawyerMapper {
    LawyerDto domainToDto(Lawyer lawyer);
    Lawyer dtoToDomain(LawyerDto lawyerDto);
}
