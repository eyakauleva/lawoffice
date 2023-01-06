package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.persistence.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService{
    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<com.solvd.course.lawoffice.domain.Service> getAllServices(){
        return serviceRepository.getAllServices();
    }

    public com.solvd.course.lawoffice.domain.Service getServiceById(Long serviceId){
        return serviceRepository.getServiceById(serviceId);
    }
}
