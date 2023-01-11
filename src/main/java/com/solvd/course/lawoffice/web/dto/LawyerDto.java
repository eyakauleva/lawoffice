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
@EqualsAndHashCode(callSuper = false)
public class LawyerDto extends UserDto {

    @NotNull(groups = ComplexTypeGroup.class, message = "Lawyer's id cannot be null")
    private Long lawyerId;

    private String description;

    private Float experience;

    private List<FacilityDto> facilities;

}
