package com.solvd.course.lawoffice.persistence.jdbc.mapper;

import com.solvd.course.lawoffice.domain.Lawyer;
import com.solvd.course.lawoffice.domain.consultation.Consultation;
import com.solvd.course.lawoffice.domain.user.User;
import lombok.SneakyThrows;

import java.sql.ResultSet;

public class ConsultationMapper {

    @SneakyThrows
    public static Consultation mapRow(ResultSet rs) {
        User user = new User();
        user.setUserId(rs.getLong("lawyer_user_id"));
        user.setName(rs.getString("lawyer_user_name"));
        user.setSurname(rs.getString("lawyer_user_surname"));
        Lawyer lawyer = new Lawyer(user);
        lawyer.setLawyerId(rs.getLong("consultation_lawyer_id"));
        Consultation consultation = new Consultation();
        consultation.setId(rs.getLong("consultation_id"));
        consultation.setVisitTime(rs.getTimestamp("consultation_visit_time").toLocalDateTime());
        consultation.setLawyer(lawyer);
        long clientId = rs.getLong("consultation_client_id");
        if(clientId != 0) {
            consultation.setClient(new User(clientId));
        }
        return consultation;
    }

}
