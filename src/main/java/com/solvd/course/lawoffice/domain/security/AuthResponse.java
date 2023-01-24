package com.solvd.course.lawoffice.domain.security;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Model with authorization data")
public class AuthResponse {

    @Schema(description = "Authorization bearer token")
    private String token;

}
