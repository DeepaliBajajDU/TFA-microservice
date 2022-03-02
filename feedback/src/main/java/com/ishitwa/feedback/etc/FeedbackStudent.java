package com.ishitwa.feedback.etc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FeedbackStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long key;
    private long feedbackId;
    private long studentId;
}
