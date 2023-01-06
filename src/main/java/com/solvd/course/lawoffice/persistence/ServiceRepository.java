package com.solvd.course.lawoffice.persistence;

import com.solvd.course.lawoffice.domain.Service;

import java.util.List;

public interface ServiceRepository {
    List<Service> getLawyerServices(Long lawyerId);
    List<Service> getAllServices();
    Service getServiceById(Long id);
}
