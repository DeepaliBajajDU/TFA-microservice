package com.ishitwa.Analytics_Microservice.repository;

import com.ishitwa.Analytics_Microservice.etc.TeacherStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherStudentRepo extends JpaRepository<TeacherStudent,Long> {

    List<TeacherStudent> findTeacherStudentsByStudentId(long studentId);
}
