package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.persistence.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceService{
    private final ServiceRepository serviceRepository;

    public List<com.solvd.course.lawoffice.domain.Service> getAll(){
        return serviceRepository.getAll();
    }

    public com.solvd.course.lawoffice.domain.Service getById(Long serviceId){
        return serviceRepository.getById(serviceId);
    }
}
