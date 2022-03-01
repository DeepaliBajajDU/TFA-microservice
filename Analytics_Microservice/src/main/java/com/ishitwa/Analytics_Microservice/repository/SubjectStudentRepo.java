package com.ishitwa.Analytics_Microservice.repository;

import com.ishitwa.Analytics_Microservice.etc.SubjectStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectStudentRepo extends JpaRepository<SubjectStudent,Long> {

    List<SubjectStudent> findSubjectStudentsByStudentId(long studentId);
}
