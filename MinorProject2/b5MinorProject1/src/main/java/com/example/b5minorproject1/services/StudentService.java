package com.example.b5minorproject1.services;

import com.example.b5minorproject1.dtos.CreateStudentResponse;
import com.example.b5minorproject1.models.Authority;
import com.example.b5minorproject1.models.Student;
import com.example.b5minorproject1.models.StudentStatus;
import com.example.b5minorproject1.models.User;
import com.example.b5minorproject1.repositories.RedisRepository;
import com.example.b5minorproject1.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RedisRepository redisRepository;

    @Autowired
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    public CreateStudentResponse create(Student student) {
        User user = student.getUser();
        user = this.userService.createUser(user, Authority.STUDENT);
        student.setUser(user);
        Student savedStudent = this.studentRepository.save(student);
        return CreateStudentResponse.builder()
                .student(savedStudent)
                .build();

    }

    public CreateStudentResponse get(Integer id) {

        Student student = this.redisRepository.get(id);

        if (student == null) {
            student = this.studentRepository.findById(id).orElse(null);
        }



        if (student == null) {
            return null;
        }
        //Assignment :- I want you to process storing in redis parallelly in a thread;
        this.redisRepository.add(student);
        return CreateStudentResponse.builder()
                .student(student)
                .build();
    }

    public CreateStudentResponse update(Student student, Integer id) {


        /*
        * way2
        * student.setId(id)
        * this.studentRepository.save(student);
        *
        * */
        Student existingStudent = this.studentRepository.findById(id).orElse(null);
        if (existingStudent == null) {
            return null;
        }
        Student mergedStudent = this.merge(student, existingStudent);

        mergedStudent = this.studentRepository.save(mergedStudent);
        return CreateStudentResponse.builder()
                .student(mergedStudent)
                .build();
    }

    public Student merge(Student incomingStudent, Student existingStudent) {

        HashMap<String, Object> incoming = this.objectMapper.convertValue(incomingStudent, HashMap.class);
        HashMap<String, Object> existing = this.objectMapper.convertValue(existingStudent, HashMap.class);

        for (String key : incoming.keySet()) {
            if(incoming.get(key)!=null){
                existing.put(key, incoming.get(key));
            }
        }

        Student mergedStudent = objectMapper.convertValue(existing, Student.class);
        return mergedStudent;


    }

    public CreateStudentResponse delete(Integer id) {


        this.studentRepository.deactivate(id, StudentStatus.INACTIVE);
        Student student = this.studentRepository.findById(id).orElse(null);
        return CreateStudentResponse.builder()
                .student(student)
                .build();
    }
}
