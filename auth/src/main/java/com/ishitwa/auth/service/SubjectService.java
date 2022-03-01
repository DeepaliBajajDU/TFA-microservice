package com.ishitwa.auth.service;

import com.ishitwa.auth.etc.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SubjectService {

    private final RestTemplate restTemplate;

    @Autowired
    public SubjectService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    public Subject findSubjectById(long subjectId) {
        return restTemplate.getForObject("http://ANALYTICS-SERVICE/subject/find/"+subjectId,Subject.class);
    }
}
