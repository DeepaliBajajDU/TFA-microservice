package com.ishitwa.feedback.repository;

import com.ishitwa.feedback.etc.FeedbackTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackTeacherRepo extends JpaRepository<FeedbackTeacher,Long> {
}
