package com.solvd.course.lawoffice.domain.affair;

import com.solvd.course.lawoffice.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Affair {

    private Long id;
    private User client;
    private String name;
    private AffairStatus status;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;

}
