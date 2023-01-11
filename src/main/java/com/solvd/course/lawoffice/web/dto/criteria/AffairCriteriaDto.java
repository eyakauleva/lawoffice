package com.solvd.course.lawoffice.web.dto.criteria;

import com.solvd.course.lawoffice.domain.affair.AffairStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class AffairCriteriaDto {

    private AffairStatus status;

}
