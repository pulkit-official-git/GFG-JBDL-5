package com.example.batch5redis.controllers;

import com.example.batch5redis.dtos.CreatePersonRequest;
import com.example.batch5redis.models.Person;
import com.example.batch5redis.services.HashFieldValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hash")
public class HashFieldValueController {

    @Autowired
    HashFieldValueService hashFieldValueService;



    @PostMapping("/add")
    public Person add(@RequestBody CreatePersonRequest createPersonRequest) {
        return this.hashFieldValueService.add(createPersonRequest.toPerson());
    }

    @GetMapping("/getAll")
    public Person getAll(@RequestParam String id) {
        return this.hashFieldValueService.getAll(id);
    }

    @GetMapping("/get")
    public Object get(@RequestParam String id,
                      @RequestParam String field) {
        return this.hashFieldValueService.get(id,field);
    }

    @DeleteMapping("/delete")
    public void deleteField(@RequestParam String id,
                            @RequestParam String field){
    this.hashFieldValueService.delete(id,field);
    }

}
