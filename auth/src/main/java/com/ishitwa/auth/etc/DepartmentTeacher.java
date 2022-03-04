package com.ishitwa.auth.etc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentTeacher {
    private long key;
    private long departmentId;
    private long teacherId;
}
