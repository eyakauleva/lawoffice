package com.solvd.course.lawoffice.web.mapper;

import com.solvd.course.lawoffice.domain.Lawyer;
import com.solvd.course.lawoffice.web.dto.LawyerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, LawyerMapper.class})
public interface AffairMapper {
    LawyerDto domainToDto(Lawyer lawyer);
    Lawyer dtoToDomain(LawyerDto lawyerDto);
}
