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
    private List<LServ> LServs;

    public Lawyer(Long id, User user) {
        this.id = id;
        this.user = user;
    }

    public Lawyer(Long id, String description, Float experience, User user) {
        this.id = id;
        this.description = description;
        this.experience = experience;
        this.user = user;
    }
}
