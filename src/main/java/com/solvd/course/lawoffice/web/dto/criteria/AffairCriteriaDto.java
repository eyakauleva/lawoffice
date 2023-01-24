package com.solvd.course.lawoffice.web.dto.criteria;

import com.solvd.course.lawoffice.domain.affair.AffairStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Criteria to get affairs by")
public class AffairCriteriaDto {

    @Schema(description = "Affair's status")
    private AffairStatus status;

}
