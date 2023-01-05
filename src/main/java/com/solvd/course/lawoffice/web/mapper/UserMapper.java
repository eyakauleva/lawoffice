package com.solvd.course.lawoffice.web.mapper;

import com.solvd.course.lawoffice.domain.User;
import com.solvd.course.lawoffice.web.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto domainToDto(User user);
    User dtoToDomain(UserDto userDto);
}
