package com.solvd.course.lawoffice.domain.consultation;

import com.solvd.course.lawoffice.domain.Lawyer;
import com.solvd.course.lawoffice.domain.user.User;
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
    private User client;

}
