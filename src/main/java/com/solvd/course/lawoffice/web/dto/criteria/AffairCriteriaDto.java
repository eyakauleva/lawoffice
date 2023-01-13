package com.solvd.course.lawoffice.web.dto.criteria;

import com.solvd.course.lawoffice.domain.affair.AffairStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AffairCriteriaDto {

    private AffairStatus status;

}
