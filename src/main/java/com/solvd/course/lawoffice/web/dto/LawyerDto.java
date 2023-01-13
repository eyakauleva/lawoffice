package com.solvd.course.lawoffice.web.dto;

import com.solvd.course.lawoffice.web.validation.IdIsRequiredGroup;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LawyerDto extends UserDto {

    @NotNull(groups = IdIsRequiredGroup.class, message = "Lawyer's id cannot be null")
    private Long lawyerId;

    private String description;

    private Float experience;

    private LocalDate startDate;

    private List<FacilityDto> facilities;

}
