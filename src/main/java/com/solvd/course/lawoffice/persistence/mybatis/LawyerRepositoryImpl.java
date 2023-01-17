package com.solvd.course.lawoffice.persistence.mybatis;

import com.solvd.course.lawoffice.persistence.LawyerRepository;
import org.apache.ibatis.annotations.Mapper;

@Mapper
//@ConditionalOnProperty(prefix = "repository", name = "impl", havingValue = "mybatis")
public interface LawyerRepositoryImpl extends LawyerRepository {
}
