package com.solvd.course.lawoffice.web.dto;

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
    private LocalDateTime visitTime;
    private LawyerDto lawyer;
    private UserDto user;
}
