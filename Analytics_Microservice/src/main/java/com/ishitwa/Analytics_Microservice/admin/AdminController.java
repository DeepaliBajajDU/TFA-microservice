package com.ishitwa.Analytics_Microservice.admin;

import com.ishitwa.Analytics_Microservice.etc.DepartmentTeacher;
import com.ishitwa.Analytics_Microservice.subject.Subject;
import com.ishitwa.Analytics_Microservice.etc.Teacher;
import com.ishitwa.Analytics_Microservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/student/addTeacher/{teacherId}/{studentId}")
    public ResponseEntity<?> addTeacherToStudent(
            @PathVariable long teacherId,
            @PathVariable long studentId
    ){
        try{
            Teacher t=userService.addTeacherToStudent(
                    studentId,teacherId
            );
            return new ResponseEntity<>(t, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("/student/addSubject/{subjectId}/{studentId}")
    public ResponseEntity<?> addSubjectToStudent(
            @PathVariable long subjectId,
            @PathVariable long studentId
    ){
        try{
            Subject s=userService.addSubjectToStudent(studentId,subjectId);
            return new ResponseEntity<>(s,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("/teacher/addSubject/{subjectId}/{teacherId}")
    public ResponseEntity<?> addSubjectToTeacher(
            @PathVariable long subjectId,
            @PathVariable long teacherId
    ){
        try{
            Subject s=userService.addSubjectToTeacher(teacherId,subjectId);
            return new ResponseEntity<>(s,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/department/addTeacher/{teacherId}/{departmentId}")
    public ResponseEntity<?> addTeacherToDepartment(
            @PathVariable long teacherId,
            @PathVariable long departmentId
    ){
        try{
            DepartmentTeacher departmentTeacher=userService.addTeacherToDepartment(teacherId,departmentId);
            return ResponseEntity.ok().body(departmentTeacher);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
