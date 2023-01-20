package com.solvd.course.lawoffice.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long userId;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private UserStatus status;
    private Role role;

    public User(Long userId) {
        this.userId = userId;
    }

    public User(Long userId, String name, String surname) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
    }

    public User(User user) {
        this.userId = user.getUserId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.status = user.getStatus();
        this.role = user.getRole();
    }

}
