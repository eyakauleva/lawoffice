package com.solvd.course.lawoffice.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Review {
    private Long id;
    private String description;
    private Integer grade;
    private LocalDateTime reviewTime;
    private User user;

}
