package com.ishitwa.feedback.service;

import com.ishitwa.feedback.etc.FeedbackStudent;
import com.ishitwa.feedback.etc.FeedbackTeacher;
import com.ishitwa.feedback.etc.Student;
import com.ishitwa.feedback.etc.Teacher;
import com.ishitwa.feedback.feedback.Feedback;
import com.ishitwa.feedback.feedback.FeedbackService;
import com.ishitwa.feedback.repository.FeedbackStudentRepo;
import com.ishitwa.feedback.repository.FeedbackTeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    private final RestTemplate restTemplate;
    private final FeedbackService feedbackService;
    private final FeedbackTeacherRepo feedbackTeacherRepo;
    private final FeedbackStudentRepo feedbackStudentRepo;

    @Autowired
    public UserService(RestTemplate restTemplate,
                       FeedbackService feedbackService,
                       FeedbackStudentRepo feedbackStudentRepo,
                       FeedbackTeacherRepo feedbackTeacherRepo){
        this.restTemplate=restTemplate;
        this.feedbackService=feedbackService;
        this.feedbackStudentRepo=feedbackStudentRepo;
        this.feedbackTeacherRepo=feedbackTeacherRepo;
    }

    public Student getStudentFromId(long studentId) {
        Student s= restTemplate.getForObject("http://AUTH-SERVICE/student/details/"+studentId,Student.class);
        return s;

    }

    public Teacher getTeacherFromId(long teacherId) {
        return restTemplate.getForObject("http://AUTH-SERVICE/teacher/"+teacherId,Teacher.class);
    }

    public List<Feedback> getFeedbackList(Teacher teacher) {
        return feedbackService.getFeedbackOfTeacher(teacher.getTeacherId());
    }

    public List<Feedback> getFeedbacks(Student student) {
        return feedbackService.getFeedbackByStudents(student.getStudentId());
    }

    public void addFeedbackToTeacher(FeedbackTeacher feedbackTeacher){
        feedbackTeacherRepo.save(feedbackTeacher);
    }

    public void addFeedbackToStudent(FeedbackStudent feedbackStudent){
        feedbackStudentRepo.save(feedbackStudent);
    }

    public void updateTeacher(Teacher teacher) {
        restTemplate.postForObject("http://AUTH-SERVICE/teacher/updateTeacher",teacher,Teacher.class);
    }
}
