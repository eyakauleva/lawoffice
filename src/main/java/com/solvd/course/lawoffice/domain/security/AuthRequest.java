package com.solvd.course.lawoffice.domain.security;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Model to get authorization data")
public class AuthRequest {

    @Schema(description = "User's login")
    private String login;

    @Schema(description = "User's password")
    private String password;

}
