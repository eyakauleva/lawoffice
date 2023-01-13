package com.solvd.course.lawoffice.domain.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationCriteria {

    private boolean unoccupiedOnly;
    private Long lawyerId;
    private Long clientId;
    private LocalDateTime visitTime;

}
