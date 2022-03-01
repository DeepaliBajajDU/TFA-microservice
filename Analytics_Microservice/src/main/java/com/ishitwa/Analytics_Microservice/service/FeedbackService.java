package com.ishitwa.Analytics_Microservice.service;

import com.ishitwa.Analytics_Microservice.etc.Feedback;
import com.ishitwa.Analytics_Microservice.etc.Teacher;
import com.ishitwa.Analytics_Microservice.etc.Teachers;
import com.ishitwa.Analytics_Microservice.etc.TeachersL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FeedbackService {

    private final RestTemplate restTemplate;

    @Autowired
    public FeedbackService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    // to complete top teachers function
    public List<Teacher> findTopTeachers() {
        TeachersL teachers=new TeachersL();
        teachers=restTemplate.getForObject("http://AUTH-SERVICE/teacher/getTop5Teachers",TeachersL.class);
        return teachers.getTeachers();
    }

    public List<Feedback> getFeedbackOfTeacher(long teacherId) {
        try {
            Feedback[] f = restTemplate.getForObject(
                    "http://FEEDBACK-SERVICE/feedback/teacher/" + teacherId
                    , Feedback[].class
            );
            List<Feedback> feedbacks=new ArrayList<>();
            for(int i=0;i<f.length;i++){
                feedbacks.add(f[i]);
            }
            return feedbacks;
        }
        catch (Exception e){
            return null;
        }
    }

    public List<Feedback> getFeedbackBySubjects(long subjectId) {
        try{
            Feedback[] f = restTemplate.getForObject(
                    "http://FEEDBACK-SERVICE/feedback/subject/"+subjectId,
                    Feedback[].class
            );
            List<Feedback> feedbacks=new ArrayList<>();
            for(int i=0;i<f.length;i++){
                feedbacks.add(f[i]);
            }
            return feedbacks;
        }catch (Exception e){
            return null;
        }
    }

    public List<Feedback> getFeedbackByTeacherAndSubject(long teacherId, long subjectId) {
        try{
            Feedback[] f = restTemplate.getForObject(
                    "http://FEEDBACK-SERVICE/feedback/subject/teacher/"+subjectId+"/"+teacherId
                    ,Feedback[].class
            );
            List<Feedback> feedbacks=new ArrayList<>();
            for(int i=0;i<f.length;i++){
                feedbacks.add(f[i]);
            }
            return feedbacks;
        }catch (Exception e){
            return null;
        }
    }

    public List<Feedback> getFeedbackByClass(long classId) {
        try{
            Feedback[] f = restTemplate.getForObject(
                    "http://FEEDBACK-SERVICE/feedback/class/"+classId
                    ,Feedback[].class
            );
            List<Feedback> feedbacks=new ArrayList<>();
            for(int i=0;i<f.length;i++){
                feedbacks.add(f[i]);
            }
            return feedbacks;
        }catch (Exception e){
            return null;
        }
    }
}
