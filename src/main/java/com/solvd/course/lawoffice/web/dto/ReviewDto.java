package com.solvd.course.lawoffice.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime reviewTime;
    private UserDto user;
}
