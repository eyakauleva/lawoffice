package com.solvd.course.lawoffice.web.dto;

import com.solvd.course.lawoffice.domain.affair.AffairStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Schema(description = "Affair information")
public class AffairDto {

    @Schema(description = "Affair's unique identifier")
    private Long id;

    @Schema(description = "Client whose affair is")
    private UserDto client;

    @Schema(description = "Affair's name")
    private String name;

    @Schema(description = "Affair's status")
    private AffairStatus status;

    @Schema(description = "Affair's description")
    private String description;

    @Schema(description = "Day when affair was started")
    private LocalDate startDate;

    @Schema(description = "Day when affair was closed")
    private LocalDate endDate;

    @Schema(description = "Total price for participating in affair")
    private BigDecimal price;

    @Schema(description = "Lawyers who were participating in affair")
    private List<LawyerDto> lawyers;

}
