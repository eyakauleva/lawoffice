package com.solvd.course.lawoffice.web.dto.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationCriteriaDto {

    private boolean unoccupiedOnly;
    private Long lawyerId;
    private Long clientId;
    private LocalDateTime visitTime;

}
