package com.ishitwa.feedback.repository;

import com.ishitwa.feedback.etc.FeedbackStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackStudentRepo extends JpaRepository<FeedbackStudent,Long> {
}
