package com.ishitwa.auth.user;

import com.ishitwa.auth.etc.Subject;
import com.ishitwa.auth.etc.Teachers;
import com.ishitwa.auth.etc.TeachersL;
import com.ishitwa.auth.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private UserService userService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private RestTemplate restTemplate;

//    @PostMapping("/signup")
//    public RegisterResponse signUp(@RequestBody RegisterUser user)throws Exception{
//        Teacher teacher= userService.saveTeacher(user);
//        return new RegisterResponse(
//                user,
//                teacher.getTeacherId()
//        );
//    }
//    moved to auth controller


//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
//        try{
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            loginRequest.getUsername(),
//                            loginRequest.getPassword()
//                    )
//            );
//        }catch (Exception e){
//            return new ResponseEntity<String>(
//                    e.getMessage(),
//                    HttpStatus.NON_AUTHORITATIVE_INFORMATION
//            );
//        }
//        User user;
//        Teacher teacher=userService.getTeacher(
//                loginRequest.getUsername()
//        );
//        if(teacher==null){
//            Student student=userService.getStudent(
//                    loginRequest.getUsername()
//            );
//            user=new User(student.getUsername(),student.getPassword());
//        }
//        else
//            user=new User(teacher.getUsername(),teacher.getPassword());
//        final UserDetails userDetails=userService.loadUserByUsername(
//                loginRequest.getUsername()
//        );
//        String token=jwtUtil.generateToken(userDetails);
//        return new ResponseEntity<LoginResponse>(
//                new LoginResponse(
//                        user.getUsername(),token
//                ),HttpStatus.OK
//        );
//    }
//    moved to auth controller

//    @GetMapping("/activate/{token}")
//    public ResponseEntity<?> activateTeacher(@PathVariable String token){
//        try{
//            Teacher teacher=userService.getTeacherFromToken(token);
//            teacher.setEnabled(true);
//            userService.updateTeacher(teacher);
//        }catch (Exception e){}
//        return new ResponseEntity<String>(
//                "Successfully enabled!",
//                HttpStatus.OK
//        );
//    }
//    moved to auth controller

//    @PostMapping("/addSubject")
//    public ResponseEntity<?> addSubject(@RequestBody Id2 id2){
//        try{
//            long subjectId= id2.getId1();
//            long teacherId= id2.getId2();
//            Subject s=userService.addSubject(teacherId,subjectId);
//            return new ResponseEntity<>(s,HttpStatus.OK);
//
//        }catch (Exception e){
//            return new ResponseEntity<>(
//                    e.getMessage(),
//                    HttpStatus.INTERNAL_SERVER_ERROR
//            );
//        }
//    }
//    should be in admin functionality
//    moved to admin controller

//    @GetMapping("/getSubjects/{id}")
//    public ResponseEntity<?> getSubjects(@PathVariable long id){
//        try{
//            Teacher teacher=userService.getTeacherFromId(id);
//            List<Subject> subjects=teacher.getSubjects();
//            return new ResponseEntity<>(
//                    subjects,
//                    HttpStatus.OK
//            );
//        }catch (Exception e){
//            return new ResponseEntity<>(
//                    e.getMessage(),
//                    HttpStatus.INTERNAL_SERVER_ERROR
//            );
//        }
//    }
//    should be in analytics functionality
//    moved to analytics controller

    @GetMapping("/getByDepartment/{id}")
    public ResponseEntity<?> getTeachersByDepartment(@PathVariable long id){
        try{
            List<Teacher> teacherList=userService.getTeachersByDepartment(id);
            return new ResponseEntity<>(teacherList,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/getByDepartmentAndSubject/{departmentId}/{subjectId}")
    public ResponseEntity<?> getByDepartmentAndSubject(
            @PathVariable long departmentId,
            @PathVariable long subjectId
    ){
        try{
            List<Teacher> teachers=userService.getTeachersByDepartment(departmentId);
            Subject subject=subjectService.findSubjectById(subjectId);
            List<Teacher> result = new ArrayList<>();
            for(Teacher t:teachers){
                List<Subject> subjects=userService.getSubjectsByTeacher(t);
                if(subjects!=null) {
                    for (Subject subject1 : subjects) {
                        if (subject1.getSubjectId() == subject.getSubjectId()) {
                            result.add(t);
                            break;
                        }
                    }
                }
            }
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/getTop5Teachers")
    public ResponseEntity<?> getTopTeachers(){
        try{
            TeachersL teacherList=userService.findTopTeachers();
            return new ResponseEntity<>(
                    teacherList,
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    @GetMapping("/username/{username}")
    public ResponseEntity<?> getTeacher(@PathVariable String username){
        try{
            Teacher teacher=userService.getTeacher(username);
            if(teacher==null){
                Student student=userService.getStudent(username);
                if(student==null) throw new Exception();
                return new ResponseEntity<>(student,HttpStatus.OK);
            }else
                return new ResponseEntity<>(teacher,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable long id){
        try{
            Teacher teacher=userService.getTeacherFromId(id);
            return new ResponseEntity<>(teacher,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Not Found!",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getTeachers/{classId}")
    public ResponseEntity<?> getTeachers(@PathVariable long classId){
        try{
            List<Student> students=userService.getAllStudents();
            List<Student> students1=new ArrayList<>();
            for(Student s:students){
                if(s.getClassId()==classId)students1.add(s);
            }
            TeachersL teachers = restTemplate.getForObject(
                    "http://ANALYTICS-SERVICE/analytics/teacher/"+students1.get(0).getStudentId(),
                       TeachersL.class);
//            List<Teacher> teachers1 = new ArrayList<>();
//            for(long tid:teachers.getTeachers()){
//                teachers1.add(userService.getTeacherFromId(tid));
//            }
            return new ResponseEntity<>(teachers.getTeachers(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getSubjectsByDepartmentAndTeacher/{departmentId}/{teacherId}")
    public ResponseEntity<?> getByDepartmentAndTeacher(
            @PathVariable long departmentId,
            @PathVariable long teacherId
    ){
        try{
            Teacher teacher=userService.getTeacherFromId(teacherId);
            List<Subject> subjects1=userService.getSubjectsByTeacher(teacher);
            List<Subject> result=new ArrayList<>();
            if(subjects1!=null)
                for(Subject s:subjects1){
                    if(s.getDepartmentId()==departmentId){
                        result.add(s);
                    }
                }
            return new ResponseEntity<>(
                    result,
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("/updateTeacher")
    public ResponseEntity<?> updateTeacher(@RequestBody Teacher teacher){
        try{
            Teacher t = userService.getTeacherFromId(teacher.getTeacherId());
            t.setAveragePoints(teacher.getAveragePoints());
            t.setTotalPoints(teacher.getTotalPoints());
            t.setFeedbacks(teacher.getFeedbacks());
            userService.updateTeacher(t);
            return ResponseEntity.ok().body(teacher);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}