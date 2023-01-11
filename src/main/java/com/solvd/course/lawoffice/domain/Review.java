package com.solvd.course.lawoffice.domain;

import com.solvd.course.lawoffice.domain.user.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Review {

    private Long id;
    private String description;
    private Integer grade;
    private LocalDateTime reviewTime;
    private User client;

}
