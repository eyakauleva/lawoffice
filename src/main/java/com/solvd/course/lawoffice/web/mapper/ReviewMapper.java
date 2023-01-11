package com.solvd.course.lawoffice.web.mapper;

import com.solvd.course.lawoffice.domain.Review;
import com.solvd.course.lawoffice.web.dto.ReviewDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ReviewMapper {

    ReviewDto domainToDto(Review review);

    List<ReviewDto> domainToDto(List<Review> reviews);

    Review dtoToDomain(ReviewDto reviewDto);

}
