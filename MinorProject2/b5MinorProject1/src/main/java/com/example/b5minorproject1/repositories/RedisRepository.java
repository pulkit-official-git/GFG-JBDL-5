package com.example.b5minorproject1.repositories;

import com.example.b5minorproject1.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RedisRepository {

    private static final String STUDENT_KEY_PREFIX = "std::";
    private static final Long STUDENT_KEY_EXPIRY = 3600L;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    private String getKey(Integer studentId){
        return STUDENT_KEY_PREFIX + studentId;
    }

    public void add(Student student){
        this.redisTemplate.opsForValue().set(getKey(student.getId()),student,STUDENT_KEY_EXPIRY, TimeUnit.SECONDS);
    }

    public Student get(Integer studentId){
        return (Student) this.redisTemplate.opsForValue().get(getKey(studentId));
    }



}
