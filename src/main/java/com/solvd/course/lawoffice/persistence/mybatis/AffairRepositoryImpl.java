package com.solvd.course.lawoffice.persistence.mybatis;

import com.solvd.course.lawoffice.persistence.AffairRepository;
import org.apache.ibatis.annotations.Mapper;

@Mapper
//@ConditionalOnProperty(prefix = "repository", name = "impl", havingValue = "mybatis")
public interface AffairRepositoryImpl extends AffairRepository {
}
