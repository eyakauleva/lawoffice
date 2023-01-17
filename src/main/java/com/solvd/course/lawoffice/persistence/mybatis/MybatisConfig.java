package com.solvd.course.lawoffice.persistence.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(annotationClass = Mapper.class)
public class MybatisConfig {
}
