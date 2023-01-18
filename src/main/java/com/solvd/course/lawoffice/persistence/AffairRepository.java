package com.solvd.course.lawoffice.persistence;

import com.solvd.course.lawoffice.domain.criteria.AffairCriteria;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AffairRepository {

    Integer countByCriteria(AffairCriteria criteria);

}
