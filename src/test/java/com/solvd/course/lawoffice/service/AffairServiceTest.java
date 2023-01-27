package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.domain.affair.AffairStatus;
import com.solvd.course.lawoffice.domain.criteria.AffairCriteria;
import com.solvd.course.lawoffice.persistence.AffairRepository;
import com.solvd.course.lawoffice.service.impl.AffairServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class AffairServiceTest {

    @Mock
    private AffairRepository affairRepository;

    @InjectMocks
    private AffairServiceImpl affairService;

    @Test
    void verifyCountByCriteriaEqualsFiveTest() {
        //given
        Integer count = 5;
        AffairCriteria affairCriteria = new AffairCriteria(AffairStatus.SUCCESS);

        Mockito.when(affairRepository.countByCriteria(affairCriteria)).thenReturn(count);

        //when
        Integer result = affairService.countByCriteria(affairCriteria);

        //then
        assertNotNull(result);
        assertEquals(count, result);
    }

}