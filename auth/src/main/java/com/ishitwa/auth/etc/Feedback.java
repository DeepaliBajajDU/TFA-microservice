package com.ishitwa.auth.etc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Feedback {
    private long feedbackId;
    private long studentId;
    private long teacherId;
    private long subjectId;
    private long departmentId;
    private long classId;
    private int ques1,ques2,ques3,ques4,ques5,ques6,ques7,ques8,ques9,ques10;
}
