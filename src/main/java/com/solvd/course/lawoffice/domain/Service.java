package com.solvd.course.lawoffice.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Service {
    private Long id;
    private String name;
    private String description;
    private Service service;

    public Service(Long id) {
        this.id = id;
    }
}
