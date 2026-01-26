package com.example.b5minorproject1.controllers;

import com.example.b5minorproject1.dtos.CreateStudentRequest;
import com.example.b5minorproject1.dtos.CreateStudentResponse;
import com.example.b5minorproject1.dtos.UpdateStudentRequest;
import com.example.b5minorproject1.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public CreateStudentResponse create(@RequestBody CreateStudentRequest createStudentRequest){

        return this.studentService.create(createStudentRequest.toStudent());
    }

    @GetMapping("/get")
    public CreateStudentResponse getStudentResponse(@RequestParam Integer id){

        return this.studentService.get(id);

    }

    @PutMapping("/update")
    public CreateStudentResponse update(@RequestBody UpdateStudentRequest updateStudentRequest,
                                        @RequestParam Integer id){
        return this.studentService.update(updateStudentRequest.toStudent(),id);

    }

    @DeleteMapping("/delete")
    public  CreateStudentResponse delete(@RequestParam Integer id){
        return this.studentService.delete(id);
    }


}
