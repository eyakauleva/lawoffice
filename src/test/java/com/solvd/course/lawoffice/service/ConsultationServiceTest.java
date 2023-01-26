package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.domain.Lawyer;
import com.solvd.course.lawoffice.domain.consultation.Consultation;
import com.solvd.course.lawoffice.domain.criteria.ConsultationCriteria;
import com.solvd.course.lawoffice.domain.exception.ResourceDoesNotExistException;
import com.solvd.course.lawoffice.domain.exception.UniqueConstraintViolationException;
import com.solvd.course.lawoffice.domain.user.User;
import com.solvd.course.lawoffice.persistence.ConsultationRepository;
import com.solvd.course.lawoffice.service.impl.ConsultationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ConsultationServiceTest {

    @Mock
    private ConsultationRepository consultationRepository;

    @InjectMocks
    private ConsultationServiceImpl consultationService;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    void verifyConsultationIsCreatedTest() {
        //given
        Consultation consultationToCreate = new Consultation();
        consultationToCreate.setVisitTime(LocalDateTime.parse("2023-01-25 19:00", dateTimeFormatter));
        consultationToCreate.setLawyer(new Lawyer(1L));

        Long createdConsultationId = 1L;
        Consultation createdConsultation = new Consultation(consultationToCreate);
        createdConsultation.setId(createdConsultationId);

        Mockito.doAnswer(invocationOnMock -> {
            Consultation consultation = invocationOnMock.getArgument(0);
            consultation.setId(createdConsultationId);
            return null;
        }).when(consultationRepository).create(consultationToCreate);

        //when
        Consultation resultConsultation = consultationService.create(consultationToCreate);

        //then
        assertNotNull(resultConsultation);
        assertEquals(createdConsultation, resultConsultation);
    }

    @Test
    void verifyLawyerAlreadyHasConsultationAtTheProvidedTimeTest() {
        //given
        Consultation consultationToCreate = new Consultation();
        consultationToCreate.setVisitTime(LocalDateTime.parse("2023-01-25 19:00", dateTimeFormatter));
        consultationToCreate.setLawyer(new Lawyer(1L));

        ConsultationCriteria criteria = new ConsultationCriteria();
        criteria.setVisitTime(consultationToCreate.getVisitTime());
        criteria.setLawyerId(consultationToCreate.getLawyer().getLawyerId());

        Mockito.when(consultationRepository.findAllByCriteria(criteria)).thenReturn(Collections.singletonList(consultationToCreate));

        //then
        assertThrows(UniqueConstraintViolationException.class, () -> {
            consultationService.create(consultationToCreate);
        });
    }

    @Test
    void verifyConsultationIsUpdatedTest() {
        //given
        Long consultationId = 1L;

        Consultation consultationNewFields = new Consultation();
        consultationNewFields.setId(consultationId);
        LocalDateTime newVisitTime = LocalDateTime.parse("2023-01-24 15:30", dateTimeFormatter);
        consultationNewFields.setVisitTime(newVisitTime);
        Lawyer newLawyer = new Lawyer(5L);
        consultationNewFields.setLawyer(newLawyer);
        User newClient = new User(6L);
        consultationNewFields.setClient(newClient);

        Consultation consultationToUpdate = new Consultation();
        consultationToUpdate.setId(consultationId);
        consultationToUpdate.setLawyer(new Lawyer(1L));
        consultationToUpdate.setVisitTime(LocalDateTime.parse("2023-01-24 10:00", dateTimeFormatter));

        Mockito.when(consultationRepository.findById(consultationId)).thenReturn(Optional.of(consultationToUpdate));

        //when
        Consultation updatedConsultation = consultationService.update(consultationNewFields);

        //then
        assertNotNull(updatedConsultation);
        assertEquals(newVisitTime, updatedConsultation.getVisitTime());
        assertEquals(newLawyer, updatedConsultation.getLawyer());
        assertEquals(newClient, updatedConsultation.getClient());
    }

    @Test
    void verifyConsultationDoesNotExistTest() {
        //given
        Long consultationId = 1L;
        Consultation consultationNewFields = new Consultation();
        consultationNewFields.setId(consultationId);

        Mockito.when(consultationRepository.findById(consultationId)).thenReturn(Optional.empty());

        //then
        assertThrows(ResourceDoesNotExistException.class, () -> {
            consultationService.update(consultationNewFields);
        });
    }

    @Test
    void verifyClientAlreadyHasConsultationAtTheProvidedTimeTest() {
        //given
        Long consultationId = 1L;

        Consultation consultationNewFields = new Consultation();
        consultationNewFields.setId(consultationId);
        LocalDateTime newVisitTime = LocalDateTime.parse("2023-01-24 15:30", dateTimeFormatter);
        consultationNewFields.setVisitTime(newVisitTime);

        Consultation consultationToUpdate = new Consultation();
        consultationToUpdate.setId(consultationId);
        consultationToUpdate.setLawyer(new Lawyer(1L));
        consultationToUpdate.setClient(new User(2L));
        consultationToUpdate.setVisitTime(LocalDateTime.parse("2023-01-24 10:00", dateTimeFormatter));

        Mockito.when(consultationRepository.findById(consultationId)).thenReturn(Optional.of(consultationToUpdate));

        ConsultationCriteria criteria = new ConsultationCriteria();
        criteria.setVisitTime(newVisitTime);
        criteria.setClientId(consultationToUpdate.getClient().getUserId());

        Mockito.lenient().when(consultationRepository.findAllByCriteria(criteria)).thenReturn(Collections.singletonList(consultationNewFields));

        //then
        assertThrows(UniqueConstraintViolationException.class, () -> {
            consultationService.update(consultationNewFields);
        });
    }

    @Test
    void verifyConsultationsAreFoundByCriteriaTest() {
        //given
        Consultation consultationToFind = new Consultation();
        consultationToFind.setId(1L);
        consultationToFind.setVisitTime(LocalDateTime.parse("2023-01-25 19:00", dateTimeFormatter));
        consultationToFind.setLawyer(new Lawyer(1L));

        ConsultationCriteria criteria = new ConsultationCriteria();
        criteria.setVisitTime(consultationToFind.getVisitTime());
        criteria.setLawyerId(consultationToFind.getLawyer().getLawyerId());

        Mockito.when(consultationRepository.findAllByCriteria(criteria)).thenReturn(Collections.singletonList(consultationToFind));

        //when
        List<Consultation> resultConsultations = consultationService.findByCriteria(criteria);

        //then
        assertNotNull(resultConsultations);
        assertEquals(Collections.singletonList(consultationToFind), resultConsultations);
    }

}