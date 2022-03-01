package com.ishitwa.Analytics_Microservice.etc;

import com.ishitwa.Analytics_Microservice.subject.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subjects {
    private List<Subject> subjects;
}
