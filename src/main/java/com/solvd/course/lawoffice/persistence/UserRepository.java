package com.solvd.course.lawoffice.persistence;

import com.solvd.course.lawoffice.domain.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserRepository {

    Optional<User> findByLogin(String login);

}
