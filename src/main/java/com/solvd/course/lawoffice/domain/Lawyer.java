package com.solvd.course.lawoffice.domain;

import com.solvd.course.lawoffice.domain.user.User;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Lawyer extends User {

    private Long lawyerId;
    private String description;
    private Float experience;
    private LocalDate startDate;
    private List<Facility> facilities;

    public Lawyer(User user){
        super(user);
    }

    public Lawyer(Long lawyerId) {
        this.lawyerId = lawyerId;
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
