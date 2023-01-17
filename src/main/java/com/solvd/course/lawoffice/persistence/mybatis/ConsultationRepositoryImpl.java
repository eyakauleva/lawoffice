package com.solvd.course.lawoffice.persistence.mybatis;

import com.solvd.course.lawoffice.persistence.ConsultationRepository;
import org.apache.ibatis.annotations.Mapper;

@Mapper
//@ConditionalOnProperty(prefix = "repository", name = "impl", havingValue = "mybatis")
public interface ConsultationRepositoryImpl extends ConsultationRepository {
}
