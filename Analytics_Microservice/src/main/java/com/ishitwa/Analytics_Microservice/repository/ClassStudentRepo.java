package com.ishitwa.Analytics_Microservice.repository;

import com.ishitwa.Analytics_Microservice.etc.ClassStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassStudentRepo extends JpaRepository<ClassStudent,Long> {
}
