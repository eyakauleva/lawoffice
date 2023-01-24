package com.solvd.course.lawoffice.web.dto.criteria;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Criteria to find consultations by")
public class ConsultationCriteriaDto {

    @Schema(description = "Whether to find only unoccupied consultations or not")
    private boolean unoccupiedOnly;

    @Schema(description = "Get consultations that belong to lawyer with this lawyerId")
    private Long lawyerId;

    @Schema(description = "Get consultations that belong to client with this userId")
    private Long clientId;

    @Schema(description = "Get consultations with this visitTime")
    private LocalDateTime visitTime;

}
