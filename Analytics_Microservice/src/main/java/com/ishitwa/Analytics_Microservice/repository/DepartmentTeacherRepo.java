package com.ishitwa.Analytics_Microservice.repository;

import com.ishitwa.Analytics_Microservice.etc.DepartmentTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentTeacherRepo extends JpaRepository<DepartmentTeacher,Long> {
}
