package com.ishitwa.auth.etc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ishitwa.auth.user.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Department {
    long departmentId;
    String departmentName;
    @JsonIgnore
    List<Teacher> teacherList;
}
