package com.solvd.course.lawoffice.web.dto;

import com.solvd.course.lawoffice.domain.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ReviewDto {
    private Long id;
    private String description;
    private Integer grade;
    private LocalDateTime reviewTime;
    private UserDto user;
}
