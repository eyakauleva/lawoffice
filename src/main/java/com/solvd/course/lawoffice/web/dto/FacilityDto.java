package com.solvd.course.lawoffice.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Facility information")
public class FacilityDto {

    @Schema(description = "Facility's unique identifier")
    private Long id;

    @Schema(description = "Facility's name")
    private String name;

    @Schema(description = "Facility's description")
    private String description;

    @Schema(description = "Facility's root facility")
    private FacilityDto facility;

}
