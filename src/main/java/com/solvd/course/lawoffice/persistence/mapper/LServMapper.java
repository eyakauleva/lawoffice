package com.solvd.course.lawoffice.persistence.mapper;

import com.solvd.course.lawoffice.domain.LServ;
import lombok.SneakyThrows;

import java.sql.ResultSet;

public class LServMapper {

    @SneakyThrows
    public static LServ mapRow(ResultSet rs) {
        LServ service = new LServ();
        service.setId(rs.getLong("service_id"));
        service.setName(rs.getString("service_name"));
        service.setDescription(rs.getString("service_description"));
        service.setService(new LServ(rs.getLong("service_parent_id")));
        return service;
    }

}
