package com.solvd.course.lawoffice.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FacilityDto {

    private Long id;
    private String name;
    private String description;
    private FacilityDto facility;

}
