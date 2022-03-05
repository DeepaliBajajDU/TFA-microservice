package com.ishitwa.Analytics_Microservice.subject;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepo extends JpaRepository<Subject,Long> {

    Subject findSubjectBySubjectId(long id);
    Subject findSubjectByName(String name);
    List<Subject> findSubjectByDepartmentId(long id);
}
