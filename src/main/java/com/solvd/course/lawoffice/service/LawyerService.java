package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.domain.Lawyer;
import com.solvd.course.lawoffice.persistence.LawyerRepository;
import com.solvd.course.lawoffice.persistence.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LawyerService {
    private final LawyerRepository lawyerRepository;
    private final ServiceRepository serviceRepository;

    @Autowired
    public LawyerService(LawyerRepository lawyerRepository, ServiceRepository serviceRepository) {
        this.lawyerRepository = lawyerRepository;
        this.serviceRepository = serviceRepository;
    }

    public List<Lawyer> getAllLawyers(){
        List<Lawyer> lawyers = lawyerRepository.getAllLawyers();
        lawyers.forEach(lawyer -> lawyer.setServices(serviceRepository.getLawyerServices(lawyer.getId())));
        return lawyers;
    }
}
