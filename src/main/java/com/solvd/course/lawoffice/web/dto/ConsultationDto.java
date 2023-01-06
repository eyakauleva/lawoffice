package com.solvd.course.lawoffice.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.solvd.course.lawoffice.web.validation.UpdateGroup;
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
    @NotNull(groups = UpdateGroup.class, message = "Consultation id cannot be null")
    private Long id;
    @NotNull(message = "Visit time cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime visitTime;
    @NotNull(message = "Consultation must contain a lawyer")
    @Valid
    private LawyerDto lawyer;
    @NotNull(groups = UpdateGroup.class, message = "Consultation must contain a client")
    @Valid
    private UserDto user;
}
