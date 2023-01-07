package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.persistence.ServiceRepository;
import com.solvd.course.lawoffice.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceService{
    private final ServiceRepository serviceRepository;
    public com.solvd.course.lawoffice.domain.Service findById(Long serviceId){
        Optional<com.solvd.course.lawoffice.domain.Service> service = serviceRepository.findById(serviceId);
        if(service.isEmpty()) throw new ResourceNotFoundException("Service (id=" + serviceId + ") does not exist");
        return service.get();
    }

    public List<com.solvd.course.lawoffice.domain.Service> findAll(){
        return serviceRepository.findAll();
    }
}
