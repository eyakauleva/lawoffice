package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.domain.Lawyer;
import com.solvd.course.lawoffice.persistence.LawyerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LawyerService {
    private final LawyerRepository lawyerRepository;

    public List<Lawyer> getAll() {
        return lawyerRepository.getAll();
    }
}
