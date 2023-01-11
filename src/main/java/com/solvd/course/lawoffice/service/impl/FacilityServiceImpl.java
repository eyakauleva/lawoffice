package com.solvd.course.lawoffice.service.impl;

import com.solvd.course.lawoffice.domain.Facility;
import com.solvd.course.lawoffice.domain.exception.ResourceNotFoundException;
import com.solvd.course.lawoffice.persistence.FacilityRepository;
import com.solvd.course.lawoffice.service.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository facilityRepository;

    public Facility findById(Long id) {
        return facilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facility (id=" + id + ") does not exist"));
    }

    public List<Facility> findAll() {
        return facilityRepository.findAll();
    }

}
