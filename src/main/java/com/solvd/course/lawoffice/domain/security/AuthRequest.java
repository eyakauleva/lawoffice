package com.solvd.course.lawoffice.domain.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    private String login;
    private String password;

}
