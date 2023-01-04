package com.solvd.course.lawoffice.web.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class LawyerDto {
    private Long id;
    private String description;
    private Float experience;
    private UserDto user;
    private ServiceDto service;
}
