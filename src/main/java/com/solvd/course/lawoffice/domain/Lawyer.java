package com.solvd.course.lawoffice.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Lawyer {
    private Long id;
    private String description;
    private Float experience;
    private User user;
    private Service service;
}
