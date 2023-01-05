package com.solvd.course.lawoffice.web.dto;

import com.solvd.course.lawoffice.domain.enums.AffairStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class AffairDto {
    private Long id;
    private UserDto user;
    private String name;
    private AffairStatus status;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;
    private List<LawyerDto> lawyers;
}