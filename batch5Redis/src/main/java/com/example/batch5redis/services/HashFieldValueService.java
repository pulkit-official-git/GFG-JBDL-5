package com.example.batch5redis.services;

import com.example.batch5redis.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@Service
public class HashFieldValueService {

    private static final String KEY_PREFIX = "person_hash::";


    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public String getKey(String id){
        return KEY_PREFIX + id;
    }

    public Person add(Person person) {
        Map<String, Object> personMap = this.objectMapper.convertValue(person, Map.class);
        this.redisTemplate.opsForHash().putAll(this.getKey(person.getId()), personMap);
        return person;
    }

    public Person getAll(String id) {
        Map personMap = this.redisTemplate.opsForHash().entries(this.getKey(id));
        Person person = this.objectMapper.convertValue(personMap, Person.class);
        return person;
    }

    public Object get(String id, String field) {
        return this.redisTemplate.opsForHash().get(this.getKey(id),field);
    }

    public void delete(String id, String field) {
        this.redisTemplate.opsForHash().delete(this.getKey(id),field);
    }
}
