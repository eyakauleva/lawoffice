package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.domain.Review;
import com.solvd.course.lawoffice.domain.exception.ResourceDoesNotExistException;
import com.solvd.course.lawoffice.domain.user.User;
import com.solvd.course.lawoffice.persistence.ReviewRepository;
import com.solvd.course.lawoffice.service.impl.ReviewServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Test
    void verifyReviewIsCreatedTest() {
        //given
        Review reviewToCreate = new Review();
        reviewToCreate.setDescription("best lawyers ever");
        reviewToCreate.setGrade(10);
        reviewToCreate.setClient(new User(1L));

        Long createdReviewId = 1L;
        Review createdReview = new Review(reviewToCreate);
        createdReview.setId(createdReviewId);

        Mockito.doAnswer(invocationOnMock -> {
            Review review = invocationOnMock.getArgument(0);
            review.setId(createdReviewId);
            return null;
        }).when(reviewRepository).create(reviewToCreate);

        //when
        Review resultReview = reviewService.create(reviewToCreate);

        //then
        assertNotNull(resultReview);
        assertEquals(createdReview, resultReview);
    }

    @Test
    void verifyReviewIsUpdatedTest() {
        //given
        Long reviewId = 1L;

        Review reviewNewFields = new Review();
        reviewNewFields.setId(reviewId);
        String newDescription = "new description";
        reviewNewFields.setDescription(newDescription);
        Integer newGrade = 8;
        reviewNewFields.setGrade(newGrade);
        User newClient = new User(5L);
        reviewNewFields.setClient(newClient);

        Review reviewToUpdate = new Review();
        reviewToUpdate.setDescription("best lawyers ever");
        reviewToUpdate.setGrade(10);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        reviewToUpdate.setReviewTime(LocalDateTime.parse("2023-01-24 10:00", dateTimeFormatter));
        reviewToUpdate.setClient(new User(1L));

        Mockito.when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(reviewToUpdate));

        //when
        Review updatedReview = reviewService.update(reviewNewFields);

        //then
        assertNotNull(updatedReview);
        assertEquals(newDescription, updatedReview.getDescription());
        assertEquals(newGrade, updatedReview.getGrade());
        assertEquals(newClient, updatedReview.getClient());
    }

    @Test
    void verifyReviewIsNotFoundTest() {
        //given
        Long reviewId = 1L;

        Review reviewToUpdate = new Review();
        reviewToUpdate.setId(reviewId);

        Mockito.when(reviewRepository.findById(reviewId)).thenReturn(Optional.empty());

        //then
        assertThrows(ResourceDoesNotExistException.class, () -> {
            reviewService.update(reviewToUpdate);
        });
    }

    @Test
    void verifyAllExistingReviewsAreFoundTest() {
        //given
        Mockito.when(reviewRepository.findAll()).thenReturn(new ArrayList<>());

        //when
        List<Review> reviews = reviewService.findAll();

        //then
        assertNotNull(reviews);
    }

}