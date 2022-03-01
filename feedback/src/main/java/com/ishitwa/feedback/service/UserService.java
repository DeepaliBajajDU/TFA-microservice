package com.ishitwa.feedback.service;

import com.ishitwa.feedback.etc.Student;
import com.ishitwa.feedback.etc.Teacher;
import com.ishitwa.feedback.feedback.Feedback;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public Student getStudentFromId(long studentId) {
    }

    public Teacher getTeacherFromId(long teacherId) {
    }

    public List<Feedback> getFeedbackList(Teacher teacher) {
    }

    public List<Feedback> getFeedbacks(Student student) {
    }
}
