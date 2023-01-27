package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.domain.Lawyer;
import com.solvd.course.lawoffice.domain.exception.ResourceDoesNotExistException;
import com.solvd.course.lawoffice.persistence.LawyerRepository;
import com.solvd.course.lawoffice.service.impl.LawyerServiceImpl;
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
class LawyerServiceTest {

    @Mock
    private LawyerRepository lawyerRepository;

    @InjectMocks
    private LawyerServiceImpl lawyerService;

    @Test
    void verifyLawyerIsFoundByIdTest() {
        //given
        Long lawyerId = 1L;

        Lawyer lawyer = new Lawyer();
        lawyer.setLawyerId(lawyerId);

        Mockito.when(lawyerRepository.findById(lawyerId)).thenReturn(Optional.of(lawyer));

        //given
        Lawyer resultLawyer = lawyerService.findById(lawyerId);

        //then
        assertNotNull(resultLawyer);
        assertEquals(lawyer.getLawyerId(), resultLawyer.getLawyerId());
    }

    @Test
    void verifyLawyerIsNotFoundByIdTest() {
        //given
        Long lawyerId = 1L;

        Mockito.when(lawyerRepository.findById(lawyerId)).thenReturn(Optional.empty());

        //then
        assertThrows(ResourceDoesNotExistException.class, () -> {
            lawyerService.findById(lawyerId);
        });
    }

    @Test
    void verifyAllExistingLawyersAreFoundTest() {
        //given
        Mockito.when(lawyerRepository.findAll()).thenReturn(new ArrayList<>());

        //when
        List<Lawyer> lawyers = lawyerService.findAll();

        //then
        assertNotNull(lawyers);
    }

}