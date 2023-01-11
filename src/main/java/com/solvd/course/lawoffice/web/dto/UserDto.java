package com.solvd.course.lawoffice.web.dto;

import com.solvd.course.lawoffice.domain.user.Role;
import com.solvd.course.lawoffice.domain.user.UserStatus;
import com.solvd.course.lawoffice.web.validation.ComplexTypeGroup;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class UserDto {

    @NotNull(groups = ComplexTypeGroup.class, message = "User's id cannot be null")
    private Long userId;

    private String name;

    private String surname;

    private String phone;

    private String email;

    private UserStatus status;

    private Role role;

}
