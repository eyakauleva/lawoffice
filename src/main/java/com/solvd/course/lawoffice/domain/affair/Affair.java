package com.solvd.course.lawoffice.domain.affair;

import com.solvd.course.lawoffice.domain.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
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
