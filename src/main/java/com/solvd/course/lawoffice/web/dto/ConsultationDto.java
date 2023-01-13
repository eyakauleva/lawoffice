package com.solvd.course.lawoffice.web.dto;

import com.solvd.course.lawoffice.web.validation.CreateGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ConsultationDto {

    private Long id;

    @NotNull(groups = CreateGroup.class, message = "Consultation's visit time cannot be null")
    private LocalDateTime visitTime;

    @NotNull(groups = CreateGroup.class, message = "Consultation must contain a lawyer")
    @Valid
    private LawyerDto lawyer;

    private UserDto client;

}
