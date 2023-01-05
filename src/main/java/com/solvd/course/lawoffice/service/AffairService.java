package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.persistence.AffairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AffairService {
    private final AffairRepository affairRepository;

    @Autowired
    public AffairService(AffairRepository affairRepository) {
        this.affairRepository = affairRepository;
    }

    public Integer countSuccessAffairs(){
        return affairRepository.countSuccessAffairs();
    }
    public Integer countFailureAffairs(){
        return affairRepository.countFailureAffairs();
    }
}
