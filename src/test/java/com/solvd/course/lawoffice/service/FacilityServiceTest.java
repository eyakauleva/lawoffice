package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.domain.Facility;
import com.solvd.course.lawoffice.domain.exception.ResourceDoesNotExistException;
import com.solvd.course.lawoffice.persistence.FacilityRepository;
import com.solvd.course.lawoffice.service.impl.FacilityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FacilityServiceTest {

    @Mock
    private FacilityRepository facilityRepository;

    @InjectMocks
    private FacilityServiceImpl facilityService;

    @Test
    void verifyFacilityIsFoundByIdTest() {
        //given
        Long facilityId = 1L;

        Facility facility = new Facility();
        facility.setId(facilityId);

        Mockito.when(facilityRepository.findById(facilityId)).thenReturn(Optional.of(facility));

        //given
        Facility resultFacility = facilityService.findById(facilityId);

        //then
        assertNotNull(resultFacility);
        assertEquals(facility.getId(), resultFacility.getId());
    }

    @Test
    void verifyFacilityIsNotFoundByIdTest() {
        //given
        Long facilityId = 1L;

        Mockito.when(facilityRepository.findById(facilityId)).thenReturn(Optional.empty());

        //then
        assertThrows(ResourceDoesNotExistException.class, () -> {
            facilityService.findById(facilityId);
        });
    }

    @Test
    void verifyAllExistingFacilitiesAreFoundTest() {
        //given
        Mockito.when(facilityRepository.findAll()).thenReturn(new ArrayList<>());

        //when
        List<Facility> facilities = facilityService.findAll();

        //then
        assertNotNull(facilities);
    }

}