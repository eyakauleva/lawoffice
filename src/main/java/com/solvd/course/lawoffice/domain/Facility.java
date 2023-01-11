package com.solvd.course.lawoffice.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Facility {

    private Long id;
    private String name;
    private String description;
    private Facility facility;

    public Facility(Long id) {
        this.id = id;
    }

}
