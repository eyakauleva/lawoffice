package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.domain.Lawyer;
import com.solvd.course.lawoffice.domain.exception.ResourceNotFoundException;
import com.solvd.course.lawoffice.persistence.LawyerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LawyerService {
    private final LawyerRepository lawyerRepository;

    public Lawyer findById(Long id){
        Optional<Lawyer> lawyer = lawyerRepository.findById(id);
        if(lawyer.isEmpty()) throw new ResourceNotFoundException("Lawyer (id=" + id + ") does not exist");
        return lawyer.get();
    }

    public List<Lawyer> findAll() {
        return lawyerRepository.findAll();
    }
}
