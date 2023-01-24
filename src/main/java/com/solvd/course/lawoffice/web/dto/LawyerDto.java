package com.solvd.course.lawoffice.web.dto;

import com.solvd.course.lawoffice.web.validation.LawyerIdRequiredGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "Lawyer information")
public class LawyerDto extends UserDto {

    @NotNull(groups = LawyerIdRequiredGroup.class, message = "Lawyer's id cannot be null")
    @Schema(description = "Lawyer's unique identifier")
    private Long lawyerId;

    @Schema(description = "Lawyer's description")
    private String description;

    @Schema(description = "Lawyer's experience before joining the company")
    private Float experience;

    @Schema(description = "Lawyer's first day at the company")
    private LocalDate startDate;

    @Schema(description = "Facilities that lawyer holds")
    private List<FacilityDto> facilities;

}
