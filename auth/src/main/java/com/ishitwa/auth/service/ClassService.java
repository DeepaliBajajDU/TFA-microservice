package com.ishitwa.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.ishitwa.auth.etc.Class;

@Service
public class ClassService {

    private final RestTemplate restTemplate;

    @Autowired
    public ClassService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    public Class getClassFromId(long classId) {
        return restTemplate.getForObject("http://ANALYTICS-SERVICE/class/"+classId,Class.class);
    }
}
