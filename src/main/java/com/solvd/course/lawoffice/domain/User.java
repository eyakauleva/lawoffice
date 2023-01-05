package com.solvd.course.lawoffice.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String status;
    private String role;

    public User(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
}
