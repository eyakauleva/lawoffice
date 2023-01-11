package com.solvd.course.lawoffice.web.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class FacilityDto {

    private Long id;
    private String name;
    private String description;
    private FacilityDto facility;

}
