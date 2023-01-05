package com.solvd.course.lawoffice.web.dto;

import com.solvd.course.lawoffice.domain.enums.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String status;
    private Role role;
}