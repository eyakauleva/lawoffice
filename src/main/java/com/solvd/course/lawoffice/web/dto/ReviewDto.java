package com.solvd.course.lawoffice.web.dto;

import com.solvd.course.lawoffice.web.validation.CreateGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReviewDto {

    private Long id;

    @NotBlank(groups = CreateGroup.class, message = "Review's description cannot be blank")
    @Size(max = 1000, message = "Review's description max length is 1000")
    private String description;

    @NotNull(groups = CreateGroup.class, message = "Review's grade cannot be null")
    @Digits(integer = 2, fraction = 0)
    private Integer grade;

    private LocalDateTime reviewTime;

    @NotNull(groups = CreateGroup.class, message = "Review must contain a client")
    @Valid
    private UserDto client;

}
