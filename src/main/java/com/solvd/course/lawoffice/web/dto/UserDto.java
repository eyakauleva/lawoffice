package com.solvd.course.lawoffice.web.dto;

import com.solvd.course.lawoffice.domain.user.Role;
import com.solvd.course.lawoffice.domain.user.UserStatus;
import com.solvd.course.lawoffice.web.validation.ClientIdRequiredGroup;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    @NotNull(groups = ClientIdRequiredGroup.class, message = "Client's id cannot be null")
    private Long userId;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private UserStatus status;
    private Role role;

}
