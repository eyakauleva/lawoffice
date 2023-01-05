package com.solvd.course.lawoffice.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Lawyer {
    private Long id;
    private String description;
    private Float experience;
    private User user;
    private List<Service> services;
}
