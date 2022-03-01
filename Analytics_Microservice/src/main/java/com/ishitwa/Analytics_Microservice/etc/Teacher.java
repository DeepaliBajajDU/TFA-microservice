package com.ishitwa.Analytics_Microservice.etc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Teacher {
    private long teacherId;
    private String username;
    private String email;
    private String fname;
    private String lname;
    private String password;
    private String roles;
    private String token;
    private boolean enabled;
    private double averagePoints;
    private int totalPoints;
    private long feedbacks;
    private long departmentId;

}
