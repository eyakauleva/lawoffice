package com.solvd.course.lawoffice.web.dto;

import com.solvd.course.lawoffice.domain.user.Role;
import com.solvd.course.lawoffice.domain.user.UserStatus;
import com.solvd.course.lawoffice.web.validation.ClientIdRequiredGroup;
import com.solvd.course.lawoffice.web.validation.LawyerUserIdRequiredGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "User information")
public class UserDto {

    @NotNull(groups = ClientIdRequiredGroup.class, message = "Client's id cannot be null")
    @NotNull(groups = LawyerUserIdRequiredGroup.class, message = "Lawyer's user id cannot be null")
    @Schema(description = "User's unique identifier")
    private Long userId;

    @Schema(description = "User's name")
    private String name;

    @Schema(description = "User's surname")
    private String surname;

    @Schema(description = "User's phone")
    private String phone;

    @Schema(description = "User's email")
    private String email;

    @Schema(description = "User's status")
    private UserStatus status;

    @Schema(description = "User's role")
    private Role role;

}
