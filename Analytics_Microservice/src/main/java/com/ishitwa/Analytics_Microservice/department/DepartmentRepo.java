package com.ishitwa.Analytics_Microservice.department;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department,Long> {
    Department findDepartmentByDepartmentId(long id);
    Department findDepartmentByDepartmentName(String name);
}
