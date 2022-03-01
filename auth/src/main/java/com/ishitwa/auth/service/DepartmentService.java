package com.ishitwa.auth.service;

import com.ishitwa.auth.etc.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DepartmentService {

    private final RestTemplate restTemplate;

    @Autowired
    public DepartmentService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    public Department getDepartmentById(long departmentId) {
        return restTemplate.getForObject("http://ANALYTICS-SERVICE/department/"+departmentId,Department.class);
    }
}
