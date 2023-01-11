package com.solvd.course.lawoffice.domain.criteria;

import com.solvd.course.lawoffice.domain.affair.AffairStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class AffairCriteria {

    private AffairStatus status;

}
