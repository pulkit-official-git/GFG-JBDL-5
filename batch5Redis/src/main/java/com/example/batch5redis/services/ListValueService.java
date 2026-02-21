package com.example.batch5redis.services;

import com.example.batch5redis.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListValueService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String LIST_KEY = "personList";


    public Long lpush(Person person) {
        return this.redisTemplate.opsForList().leftPush(LIST_KEY,person);
    }

    public Long rpush(Person person) {
        return this.redisTemplate.opsForList().rightPush(LIST_KEY,person);
    }

    public List<Person> lpop(Integer count) {
        return this.redisTemplate.opsForList()
                .leftPop(LIST_KEY,count)
                .stream()
                .map(a->(Person)a)
                .collect(Collectors.toList());
    }


    public List<Person> rpop(Integer count) {
        return this.redisTemplate.opsForList()
                .rightPop(LIST_KEY,count)
                .stream()
                .map(a->(Person)a)
                .collect(Collectors.toList());
    }

    public List<Person> lrange(Integer start, Integer end) {
        return this.redisTemplate.opsForList()
                .range(LIST_KEY,start,end)
                .stream()
                .map(a->(Person)a)
                .collect(Collectors.toList());
    }
}
