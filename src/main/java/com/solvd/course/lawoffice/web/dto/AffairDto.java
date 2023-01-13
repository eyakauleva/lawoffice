package com.solvd.course.lawoffice.web.dto;

import com.solvd.course.lawoffice.domain.affair.AffairStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class AffairDto {

    private Long id;
    private UserDto client;
    private String name;
    private AffairStatus status;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;
    private List<LawyerDto> lawyers;

}
