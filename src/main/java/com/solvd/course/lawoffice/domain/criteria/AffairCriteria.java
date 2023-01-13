package com.solvd.course.lawoffice.domain.criteria;

import com.solvd.course.lawoffice.domain.affair.AffairStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AffairCriteria {

    private AffairStatus status;

}
