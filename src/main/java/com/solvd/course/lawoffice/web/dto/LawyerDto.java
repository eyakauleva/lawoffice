package com.solvd.course.lawoffice.web.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class LawyerDto {
    private Long id;
    private String description;
    private Float experience;
    private UserDto user;
    private List<LServDto> services;
}
