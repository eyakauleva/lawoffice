package com.solvd.course.lawoffice.web.dto;

import com.solvd.course.lawoffice.web.validation.CreateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Schema(description = "Consultation information")
public class ConsultationDto {

    @Schema(description = "Consultation's unique identifier")
    private Long id;

    @NotNull(groups = CreateGroup.class, message = "Consultation's visit time cannot be null")
    @Schema(description = "Consultation's visit time")
    private LocalDateTime visitTime;

    @NotNull(groups = CreateGroup.class, message = "Consultation must contain a lawyer")
    @Valid
    @Schema(description = "Lawyer to hold a consultation")
    private LawyerDto lawyer;

    @Schema(description = "Client to visit a consultation")
    private UserDto client;

}
