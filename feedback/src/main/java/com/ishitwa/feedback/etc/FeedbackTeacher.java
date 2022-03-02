package com.ishitwa.feedback.etc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FeedbackTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long key;
    private long feedbackId;
    private long teacherId;
}
