package com.solvd.course.lawoffice.web.dto;

import com.solvd.course.lawoffice.web.validation.ComplexTypeGroup;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(groups = ComplexTypeGroup.class, message = "Lawyer's id cannot be null")
    private Long id;
    private String description;
    private Float experience;
    private UserDto user;
    private List<LServDto> services;
}
