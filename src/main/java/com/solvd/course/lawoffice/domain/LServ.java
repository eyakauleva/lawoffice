package com.solvd.course.lawoffice.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LServ {

    private Long id;
    private String name;
    private String description;
    private LServ service;

    public LServ(Long id) {
        this.id = id;
    }

}
