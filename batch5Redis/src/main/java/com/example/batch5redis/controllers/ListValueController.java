package com.example.batch5redis.controllers;

import com.example.batch5redis.dtos.CreatePersonRequest;
import com.example.batch5redis.models.Person;
import com.example.batch5redis.services.ListValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/list")
public class ListValueController {

    @Autowired
    ListValueService listValueService;

    @PostMapping("/lpush")
    public Long lpush(@RequestBody CreatePersonRequest createPersonRequest) {
        return this.listValueService.lpush(createPersonRequest.toPerson());
    }

    @PostMapping("/rpush")
    public Long rpush(@RequestBody CreatePersonRequest createPersonRequest) {
        return this.listValueService.rpush(createPersonRequest.toPerson());
    }

    @PostMapping("/lpop")
    public List<Person> lpop(@RequestParam(value = "count",required = false,defaultValue = "1") Integer count) {
        return this.listValueService.lpop(count);
    }

    @PostMapping("/rpop")
    public List<Person> rpop(@RequestParam(value = "count",required = false,defaultValue = "1") Integer count) {
        return this.listValueService.rpop(count);
    }

    @GetMapping("/lrange")
    public List<Person> lrange(@RequestParam(value = "start",required = false,defaultValue = "0") Integer start,
                               @RequestParam(value = "end",required = false,defaultValue = "-1")Integer end) {
        return this.listValueService.lrange(start,end);
    }
}
