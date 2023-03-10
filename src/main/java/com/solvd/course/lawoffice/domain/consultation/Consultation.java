package com.solvd.course.lawoffice.domain.consultation;

import com.solvd.course.lawoffice.domain.Lawyer;
import com.solvd.course.lawoffice.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consultation {

    private Long id;
    private LocalDateTime visitTime;
    private Lawyer lawyer;
    private User client;

    public Consultation(Consultation consultation) {
        this.id = consultation.id;
        this.visitTime = consultation.getVisitTime();
        this.lawyer = consultation.getLawyer();
        this.client = consultation.getClient();
    }

}
