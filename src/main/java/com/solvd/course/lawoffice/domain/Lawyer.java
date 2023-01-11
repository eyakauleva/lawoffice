package com.solvd.course.lawoffice.domain;

import com.solvd.course.lawoffice.domain.user.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Lawyer extends User {

    private Long lawyerId;
    private String description;
    private Float experience;
    private List<LServ> services;

    public Lawyer(User user){
        super(user);
    }

    public Lawyer(Long lawyerId, User user) {
        super(user);
        this.lawyerId = lawyerId;
    }

    public Lawyer(Long lawyerId, String description, Float experience, User user) {
        super(user);
        this.lawyerId = lawyerId;
        this.description = description;
        this.experience = experience;
    }

}
