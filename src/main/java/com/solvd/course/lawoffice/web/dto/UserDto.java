package com.solvd.course.lawoffice.web.dto;

import com.solvd.course.lawoffice.domain.enums.Role;
import com.solvd.course.lawoffice.domain.enums.UserStatus;
import com.solvd.course.lawoffice.web.validation.CreateGroup;
import com.solvd.course.lawoffice.web.validation.UpdateGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class UserDto {
    @NotNull(groups = UpdateGroup.class, message = "User id cannot be null")
    private Long id;
    @NotBlank(groups = UpdateGroup.class, message = "User name cannot be blank")
    @Size(max = 45, groups = {CreateGroup.class, UpdateGroup.class}, message = "User's name max length is 45 symbols")
    private String name;
    @NotBlank(groups = UpdateGroup.class, message = "User surname cannot be blank")
    @Size(max = 45, groups = {CreateGroup.class, UpdateGroup.class}, message = "User's surname max length is 45 symbols")
    private String surname;
    private String phone;
    private String email;
    private UserStatus status;
    private Role role;
}
