package com.example.batch5redis.services;

import com.example.batch5redis.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class StringValueService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String KEY_PREFIX = "person::";

    private String getKey(String id) {
        return KEY_PREFIX + id;
    }


    public String add(Person person) {
        String key = getKey(person.getId());
        this.redisTemplate.opsForValue().set(key,person,10, TimeUnit.MINUTES);
        return person.getId();
    }

    public Person get(String id) {
        String key = getKey(id);
        return (Person) this.redisTemplate.opsForValue().get(key);
    }
}
