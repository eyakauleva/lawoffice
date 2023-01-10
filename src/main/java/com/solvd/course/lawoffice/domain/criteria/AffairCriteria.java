package com.solvd.course.lawoffice.domain.criteria;

import com.solvd.course.lawoffice.domain.enums.AffairStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AffairCriteria {
    private AffairStatus status;
}
