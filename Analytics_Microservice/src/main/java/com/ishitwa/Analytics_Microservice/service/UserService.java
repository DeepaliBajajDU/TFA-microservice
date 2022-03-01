package com.ishitwa.Analytics_Microservice.service;

import com.ishitwa.Analytics_Microservice.etc.*;
import com.ishitwa.Analytics_Microservice.repository.SubjectStudentRepo;
import com.ishitwa.Analytics_Microservice.repository.SubjectTeacherRepo;
import com.ishitwa.Analytics_Microservice.repository.TeacherStudentRepo;
import com.ishitwa.Analytics_Microservice.subject.Subject;
import com.ishitwa.Analytics_Microservice.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final SubjectStudentRepo subjectStudentRepo;
    private final SubjectTeacherRepo subjectTeacherRepo;
    private final TeacherStudentRepo teacherStudentRepo;
    private final RestTemplate restTemplate;
    private final SubjectService subjectService;
    private final FeedbackService feedbackService;

    @Autowired
    public UserService(SubjectStudentRepo subjectStudentRepo,
                       SubjectTeacherRepo subjectTeacherRepo,
                       TeacherStudentRepo teacherStudentRepo,
                       RestTemplate restTemplate,
                       SubjectService subjectService,
                       FeedbackService feedbackService){
        this.subjectStudentRepo=subjectStudentRepo;
        this.subjectTeacherRepo=subjectTeacherRepo;
        this.teacherStudentRepo=teacherStudentRepo;
        this.restTemplate=restTemplate;
        this.subjectService=subjectService;
        this.feedbackService=feedbackService;
    }

    public Teacher getTeacherFromId(long id) {
        return restTemplate.getForObject("http://AUTH-SERVICE/auth/teacher/"+id,Teacher.class);
    }

    public Student getStudentFromId(long id){
        return restTemplate.getForObject("http://AUTH-SERVICE/auth/student/details/"+id,Student.class);
    }

    public List<Long> getSubjectsIds(Teacher teacher){
        List<Long> subjects = new ArrayList<>();
        List<SubjectTeacher> subjectTeachers = subjectTeacherRepo.findSubjectTeachersByTeacherId(teacher.getTeacherId());
        for(SubjectTeacher s:subjectTeachers){
            if(!subjects.contains(s.getSubjectId())){
                subjects.add(s.getSubjectId());
            }
        }
        return subjects;
    }

    public List<Long> getSubjectsIds(Student student){
        List<Long> subjects = new ArrayList<>();
        List<SubjectStudent> subjectStudents = subjectStudentRepo.findSubjectStudentsByStudentId(student.getStudentId());
        for(SubjectStudent s:subjectStudents){
            if(!subjects.contains(s.getSubjectId())){
                subjects.add(s.getSubjectId());
            }
        }
        return subjects;
    }

    public Subjects getSubjects(Teacher teacher) {
        List<Long> subjects = getSubjectsIds(teacher);
        Subjects subjects1=new Subjects();
        subjects1.setSubjects(subjectService.getSubjectsByIds(subjects));
        return subjects1;
    }

    public Subjects getSubjects(Student student){
        List<Long> subjects = getSubjectsIds(student);
        Subjects subjects1 = new Subjects();
        subjects1.setSubjects(subjectService.getSubjectsByIds(subjects));
        return subjects1;
    }

    public Teachers getTeachersByStudent(long studentId){
        List<Long> teachers = new ArrayList<>();
        List<TeacherStudent> teacherStudents=teacherStudentRepo.findTeacherStudentsByStudentId(studentId);
        for(TeacherStudent t:teacherStudents){
            if(!teachers.contains(t.getTeacherId())){
                teachers.add(t.getTeacherId());
            }
        }
        Teachers teachers1=new Teachers();
        teachers1.setTeachers(teachers);
        return teachers1;
    }

    public List<Teacher> findTopTeachers() {
        return feedbackService.findTopTeachers();
    }

    public Teacher addTeacherToStudent(long studentId, long teacherId) {
        TeacherStudent teacherStudent=new TeacherStudent();
        teacherStudent.setStudentId(studentId);
        teacherStudent.setTeacherId(teacherId);
        teacherStudentRepo.save(teacherStudent);
        return restTemplate.getForObject("http://AUTH-SERVICE/auth/teacher/"+teacherId,Teacher.class);
    }

    public Subject addSubjectToStudent(long studentId, long subjectId) {
        SubjectStudent subjectStudent=new SubjectStudent();
        subjectStudent.setStudentId(studentId);
        subjectStudent.setSubjectId(subjectId);
        subjectStudentRepo.save(subjectStudent);
        return subjectService.findSubjectById(subjectId);
    }

    public Subject addSubjectToTeacher(long teacherId, long subjectId) {
        SubjectTeacher subjectTeacher=new SubjectTeacher();
        subjectTeacher.setSubjectId(subjectId);
        subjectTeacher.setTeacherId(teacherId);
        subjectTeacherRepo.save(subjectTeacher);
        return subjectService.findSubjectById(subjectId);
    }
}
