package com.solvd.course.lawoffice.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.solvd.course.lawoffice.web.validation.CreateGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ConsultationDto {
    private Long id;
    @NotNull(groups = CreateGroup.class, message = "Consultation's visit time cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime visitTime;
    @NotNull(groups = CreateGroup.class, message = "Consultation must contain a lawyer")
    @Valid
    private LawyerDto lawyer;
    @Valid
    private UserDto user;
}
