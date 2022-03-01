package com.ishitwa.auth.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ishitwa.auth.auth.RegisterUser;
import com.ishitwa.auth.etc.Feedback;
import com.ishitwa.auth.etc.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.utility.RandomString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long studentId;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String fname;
    private String lname;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String roles;
    @JsonIgnore
    private String token;
    @JsonIgnore
    private boolean enabled;
    private long classId;
//    @Nullable
//    @ManyToMany(cascade=CascadeType.PERSIST)
//    List<Teacher> teachers;


    public Student(RegisterUser user){

        this.fname=user.getFname();
        this.lname=user.getLname();
        this.username=user.getUsername();
        this.email=user.getEmail();
        this.password=user.getPassword();

        this.token= RandomString.make(14);
        this.roles="ROLE_STUDENT";
        this.enabled=true;
//        this.teachers=new ArrayList<>();

        this.classId= user.getClassId();
    }
}