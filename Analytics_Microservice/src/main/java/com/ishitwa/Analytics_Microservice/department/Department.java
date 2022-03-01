package com.ishitwa.Analytics_Microservice.department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long departmentId;
    @Column(unique = true)
    String departmentName;
//    List<Teacher> teacherList;
}
