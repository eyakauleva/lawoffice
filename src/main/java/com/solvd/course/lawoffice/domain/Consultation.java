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
public class Consultation {
    private Long id;
    private LocalDateTime visitTime;
    private Lawyer lawyer;
    private User user;
}
