package com.solvd.course.lawoffice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facility {

    private Long id;
    private String name;
    private String description;
    private Facility facility;

    public Facility(Long id) {
        this.id = id;
    }

}
