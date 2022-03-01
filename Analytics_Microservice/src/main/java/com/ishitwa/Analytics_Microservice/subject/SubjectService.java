package com.ishitwa.Analytics_Microservice.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepo subjectRepo;

    public Subject saveSubject(Subject subject){
        subject.setName(subject.getName().toUpperCase());
        return subjectRepo.save(subject);
    }

    public Subject findSubjectById(long id){
        return subjectRepo.findSubjectBySubjectId(id);
    }

    public Subject findSubjectByName(String name){
        try{
            return subjectRepo.findSubjectByName(name);
        }catch (Exception e){
            return null;
        }
    }

    public List<Subject> getSubjectsByDepartment(long departmentId){
        return subjectRepo.findSubjectByDepartmentId(departmentId);
    }

    public List<Subject> getSubjectsByIds(List<Long> ids){
        List<Subject> subjects = new ArrayList<>();
        for(Long id:ids){
            subjects.add(subjectRepo.findSubjectBySubjectId(id));
        }
        return subjects;
    }
}
