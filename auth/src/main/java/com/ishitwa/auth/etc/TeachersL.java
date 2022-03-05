package com.ishitwa.auth.etc;

import com.ishitwa.auth.user.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeachersL {
    private List<Teacher> teachers;
}
