package com.ishitwa.auth.etc;

import com.ishitwa.auth.user.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Class {
    long classId;
    String branch;
    String section;
    int year;
    long departmentId;
}
