package com.ishitwa.Analytics_Microservice.repository;

import com.ishitwa.Analytics_Microservice.etc.SubjectTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectTeacherRepo extends JpaRepository<SubjectTeacher,Long> {
    List<SubjectTeacher> findSubjectTeachersByTeacherId(long teacherId);
}
