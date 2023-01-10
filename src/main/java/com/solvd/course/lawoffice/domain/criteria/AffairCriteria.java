package com.solvd.course.lawoffice.domain.criteria;

import com.solvd.course.lawoffice.domain.enums.AffairStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class AffairCriteria {
    private AffairStatus status;
}
