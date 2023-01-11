package com.solvd.course.lawoffice.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.solvd.course.lawoffice.web.validation.CreateGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(groups = CreateGroup.class, message = "Review's description cannot be blank")
    @Size(max = 1000, message = "Review's description max length is 1000")
    private String description;

    @NotNull(groups = CreateGroup.class, message = "Review's grade cannot be null")
    private Integer grade;

    @NotNull(groups = CreateGroup.class, message = "Review's creation time cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime reviewTime;

    @NotNull(groups = CreateGroup.class, message = "Review must contain a client")
    @Valid
    private UserDto client;

}
