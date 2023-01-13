package com.solvd.course.lawoffice.persistence.jdbc.mapper;

import com.solvd.course.lawoffice.domain.Facility;
import lombok.SneakyThrows;

import java.sql.ResultSet;

public class FacilityMapper {

    @SneakyThrows
    public static Facility mapRow(ResultSet rs) {
        Facility facility = new Facility();
        facility.setId(rs.getLong("service_id"));
        facility.setName(rs.getString("service_name"));
        facility.setDescription(rs.getString("service_description"));
        long parentId = rs.getLong("service_parent_id");
        if (parentId != 0) {
            facility.setFacility(new Facility(parentId));
        }
        return facility;
    }

}
