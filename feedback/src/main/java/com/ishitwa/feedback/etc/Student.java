package com.ishitwa.feedback.etc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {
    private long studentId;
    private String username;
    private String email;
    private String fname;
    private String lname;
    private String password;
    private String roles;
    private String token;
    private boolean enabled;
    private long classId;
}