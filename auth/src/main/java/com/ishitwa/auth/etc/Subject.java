package com.ishitwa.auth.etc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subject {
    private long subjectId;
    private String name;
    private long departmentId;
}
