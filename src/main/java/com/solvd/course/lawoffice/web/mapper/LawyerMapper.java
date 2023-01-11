package com.solvd.course.lawoffice.web.mapper;

import com.solvd.course.lawoffice.domain.Lawyer;
import com.solvd.course.lawoffice.web.dto.LawyerDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = FacilityMapper.class)
public interface LawyerMapper {

    LawyerDto domainToDto(Lawyer lawyer);

    List<LawyerDto> domainToDto(List<Lawyer> lawyers);

    Lawyer dtoToDomain(LawyerDto lawyerDto);

}
