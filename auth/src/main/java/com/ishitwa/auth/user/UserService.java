package com.ishitwa.auth.user;

import com.ishitwa.auth.etc.*;
import com.ishitwa.auth.auth.RegisterUser;
import com.ishitwa.auth.etc.Class;
import com.ishitwa.auth.service.ClassService;
import com.ishitwa.auth.service.DepartmentService;
import com.ishitwa.auth.service.SubjectService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ClassService classService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public UserDetailsService getUserDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                try {
                    Student student = studentRepo.findStudentByUsername(username);
                    return new UserDetails(student);
                } catch (Exception e) {
                    Teacher teacher = teacherRepo.findTeacherByUsername(username);
                    return new UserDetails(teacher);
                }
            }
        };
    }

    public UserDetails loadUserByUsername(String username){
        try{
            Student student= studentRepo.findStudentByUsername(username);
            return new UserDetails(student);
        }catch (Exception e){
            Teacher teacher= teacherRepo.findTeacherByUsername(username);
            return new UserDetails(teacher);
        }
    }

    public Student saveStudent(RegisterUser user)throws Exception{
        try{
            PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

            user.setPassword(passwordEncoder.encode(user.getPassword()));

            Student student=new Student(user);

            Class c= classService.getClassFromId(user.getClassId());

//            f(c!=null) {
//                List<Student> students=c.getStudents();
//                students.add(student);
//                c.setStudents(students);
//            }i
            return studentRepo.save(student);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Student getStudentFromId(Long id)throws Exception{
        return studentRepo.findStudentByStudentId(id);
    }

//    public Teacher addTeacherToStudent(long studentId,long teacherId)throws Exception{
//        Student student = studentRepo.findStudentByStudentId(studentId);
//        Teacher teacher;
//        teacher = teacherRepo.findTeacherByTeacherId(teacherId);
//
//        List<Teacher> teacherList=student.getTeachers();
//        boolean flag=false;
//        if(teacherList.size()>0) {
//            for (Teacher t : teacherList) {
//                if (t.getTeacherId() == teacherId) {
//                    flag = true;
//                    break;
//                }
//            }
//        }
//        if(!flag){
//            teacherList.add(teacher);
//            updateStudent(student);
//        }
//        return teacher;
//    }

    public Teacher saveTeacher(RegisterUser user)throws Exception{
        try{
            Department department=departmentService.getDepartmentById(
                    user.getDepartmentId()
            );
            PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Teacher teacher=new Teacher(user);
//            List<Teacher> teacherList=departmentService.getTeacherList(teacher);
//            teacherList.add(teacher);
//            department.setTeacherList(teacherList);
            departmentService.addTeacherToDepartment(teacher);
            return teacherRepo.save(teacher);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Teacher getTeacherFromId(Long id)throws Exception{
        return teacherRepo.findTeacherByTeacherId(id);
    }

    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }

    public Student getStudentFromToken(String token){
        return studentRepo.findStudentByToken(token);
    }

    public Teacher getTeacherFromToken(String token){
        return teacherRepo.findTeacherByToken(token);
    }

    public void updateTeacher(Teacher teacher){
        teacherRepo.save(teacher);
    }

    public void updateStudent(Student student){
        studentRepo.save(student);
    }

    public Student getStudent(String username){
        try {
            return studentRepo.findStudentByUsername(username);
        }catch (Exception e){
            return null;
        }
    }

    public Teacher getTeacher(String username){
        try{
            return teacherRepo.findTeacherByUsername(username);
        }catch (Exception e){
            return null;
        }
    }

//    public Subject addSubject(long teacherId, long subjectId)throws Exception{
//        Teacher teacher=getTeacherFromId(teacherId);
//        Subject subject=subjectService.findSubjectById(subjectId);
//
//        List<Subject> subjects=getSubjectsByTeacher(teacher);
//        boolean flag=false;
//        if(subjects.size()>0){
//            for(Subject s:subjects){
//                if(s.getSubjectId()==subjectId){
//                    flag=true;
//                    break;
//                }
//            }
//        }
//        if(!flag){
//            subjects.add(subject);
//            updateTeacher(teacher);
//        }
//
//        return subject;
//    }

    public List<Teacher> getTeachersByDepartment(long departmentId){
        return teacherRepo.findTeacherByDepartmentId(departmentId);
    }

//    public Subject addSubjectToStudent(long studentId,long subjectId)throws Exception{
//        Student student=getStudentFromId(studentId);
//        Subject subject=subjectService.findSubjectById(subjectId);
//
//        List<Subject> subjects=getSubjectsByStudent(student);
//        boolean flag=false;
//
//
//        if(subjects.size()>0){
//            for(Subject subject1:subjects){
//                if(subject1.getSubjectId()==subjectId){
//                    flag=true;
//                    break;
//                }
//            }
//        }
//
//        if(!flag){
//            subjects.add(subject);
//            updateStudent(student);
//            return subject;
//        }else{
//            throw new Exception("Subject already present!");
//        }
//    }

    public List<Teacher> findTopTeachers(){
        return teacherRepo.findTop5ByOrderByAveragePointsDesc();
    }

    public List<Student> findStudentByClass(long classId){
        try{
            return studentRepo.findStudentByClassId(classId);
        }catch (Exception e){
            return null;
        }
    }

    public List<Subject> getSubjectsByTeacher(Teacher t) {
        try {
            Subjects subjects = restTemplate.getForObject("http://ANALYTICS-SERVICE/analytics/teacher/getSubjects/" + t.getTeacherId(), Subjects.class);
            return subjects.getSubjects();
        }catch (Exception e){
            return null;
        }
    }

    public List<Subject> getSubjectsByStudent(Student student) {
        try{
            Subjects subjects=restTemplate.getForObject("http://ANALYTICS-SERVICE/analytics/student/getSubjects/"+student.getStudentId(), Subjects.class);
            return subjects.getSubjects();
        }catch (Exception e){
            return null;
        }
    }
}
