package com.solvd.course.lawoffice.service.impl;

import com.solvd.course.lawoffice.domain.Lawyer;
import com.solvd.course.lawoffice.domain.exception.ResourceNotFoundException;
import com.solvd.course.lawoffice.persistence.LawyerRepository;
import com.solvd.course.lawoffice.service.LawyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LawyerServiceImpl implements LawyerService {

    private final LawyerRepository lawyerRepository;

    public Lawyer findById(Long id) {
        return lawyerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lawyer (id=" + id + ") does not exist"));
    }

    public List<Lawyer> findAll() {
        return lawyerRepository.findAll();
    }

}
