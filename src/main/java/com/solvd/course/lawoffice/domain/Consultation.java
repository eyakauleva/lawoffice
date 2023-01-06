package com.solvd.course.lawoffice.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Consultation {
    private Long id;
    private LocalDateTime visitTime;
    private Lawyer lawyer;
    private User user;
}
