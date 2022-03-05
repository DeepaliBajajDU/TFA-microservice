package com.ishitwa.auth.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepo extends JpaRepository<Teacher,Long> {

    Teacher findTeacherByUsername(String username);

    Teacher findTeacherByTeacherId(Long id);

    Teacher findTeacherByToken(String token);

    List<Teacher> findTeacherByDepartmentId(long id);

    List<Teacher> findTop5ByOrderByAveragePointsDesc();


}
