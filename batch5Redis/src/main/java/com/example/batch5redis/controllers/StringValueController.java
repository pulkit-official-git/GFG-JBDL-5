package com.example.batch5redis.controllers;


import com.example.batch5redis.dtos.CreatePersonRequest;
import com.example.batch5redis.models.Person;
import com.example.batch5redis.services.StringValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/string")
public class StringValueController {

    @Autowired
    private StringValueService stringValueService;

    @PostMapping("/add")
    public String add(@RequestBody CreatePersonRequest createPersonRequest) {

        return this.stringValueService.add(createPersonRequest.toPerson());

    }

    @GetMapping("/get")
    public Person get(@RequestParam String id) {
        return this.stringValueService.get(id);
    }
}
