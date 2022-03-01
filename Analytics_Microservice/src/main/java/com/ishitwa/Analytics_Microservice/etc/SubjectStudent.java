package com.ishitwa.Analytics_Microservice.etc;

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
public class SubjectStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long key;
    long subjectId;
    long studentId;
}
