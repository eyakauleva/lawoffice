package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.domain.LServ;
import com.solvd.course.lawoffice.persistence.LServRepository;
import com.solvd.course.lawoffice.domain.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LServService {
    private final LServRepository LServRepository;
    public LServ findById(Long serviceId){
        Optional<LServ> service = LServRepository.findById(serviceId);
        if(service.isEmpty()) throw new ResourceNotFoundException("Service (id=" + serviceId + ") does not exist");
        return service.get();
    }

    public List<LServ> findAll(){
        return LServRepository.findAll();
    }
}
