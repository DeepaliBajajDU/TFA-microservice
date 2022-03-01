package com.ishitwa.Analytics_Microservice.analytics;

import com.ishitwa.Analytics_Microservice.etc.Feedback;
import com.ishitwa.Analytics_Microservice.etc.Student;
import com.ishitwa.Analytics_Microservice.etc.Subjects;
import com.ishitwa.Analytics_Microservice.etc.Teacher;
import com.ishitwa.Analytics_Microservice.service.*;
import com.ishitwa.Analytics_Microservice.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private UserService userService;

    @PostMapping("/feedbacks/teacher/{id}")
    public ResponseEntity<?> getFeedbacksByTeacher(@PathVariable long id){
        try {
            List<Feedback> feedbacks = feedbackService.getFeedbackOfTeacher(id);
            return new ResponseEntity<>(feedbacks, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(
                    "Feedback not found!",
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping("/feedbacks/subject/{id}")
    public ResponseEntity<?> getFeedbacksBySubject(@PathVariable long id){
        try{
            List<Feedback> feedbacks=feedbackService.getFeedbackBySubjects(id);
            return new ResponseEntity<>(feedbacks,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(
                    "Feedbacks not found!",
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping("/feedbacks/teacher/subject/{teacherId}/{subjectId}")
    public ResponseEntity<?> getFeedbackByTeacherAndSubject(
            @PathVariable long teacherId,
            @PathVariable long subjectId
    ){
        try{
            List<Feedback> feedbacks=feedbackService.getFeedbackByTeacherAndSubject(teacherId,subjectId);
            return new ResponseEntity<>(feedbacks,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(
                    "Feedbacks not found!",
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping("/feedbacks/class/{id}")
    public ResponseEntity<?> getFeedbackByClass(@PathVariable long id){
        try{
            List<Feedback> feedbacks=feedbackService.getFeedbackByClass(id);
            return new ResponseEntity<>(
                    feedbacks,
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    "Feedbacks not found!",
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping("/teacher/getSubjects/{id}")
    public ResponseEntity<?> getSubjectsByTeacher(@PathVariable long id){
        try{
            Teacher teacher=userService.getTeacherFromId(id);
            Subjects subjects=userService.getSubjects(teacher);
            return new ResponseEntity<>(
                    subjects,
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/student/getSubjects/{id}")
    public ResponseEntity<?> getSubjectsByStudent(@PathVariable long id){
        try{
            Student student=userService.getStudentFromId(id);
            Subjects subjects=userService.getSubjects(student);
            return ResponseEntity.ok().body(subjects);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getTop5Teachers")
    public ResponseEntity<?> getTopTeachers(){
        try{
            List<Teacher> teacherList=userService.findTopTeachers();
            return new ResponseEntity<>(
                    teacherList,
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/teacher/{studentId}")
    public ResponseEntity<?> getTeacherByStudent(@PathVariable long studentId){
        try{
            return ResponseEntity.ok().body(userService.getTeachersByStudent(studentId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
