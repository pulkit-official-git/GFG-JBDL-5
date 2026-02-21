package com.example.b5minorproject1.controllers;

import com.example.b5minorproject1.dtos.CreateStudentRequest;
import com.example.b5minorproject1.dtos.CreateStudentResponse;
import com.example.b5minorproject1.dtos.UpdateStudentRequest;
import com.example.b5minorproject1.models.User;
import com.example.b5minorproject1.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public CreateStudentResponse create(@RequestBody CreateStudentRequest createStudentRequest){
//        System.out.println("reached>>>>>>>>>>>>>");
        return this.studentService.create(createStudentRequest.toStudent());
    }

    @GetMapping("/admin/get")
    public CreateStudentResponse getStudentResponseByAdmin(@RequestParam Integer id){

        return this.studentService.get(id);

    }
    @GetMapping("/get")
    public CreateStudentResponse getStudentResponse(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User user = (User) authentication.getPrincipal();
        Integer id = user.getStudent().getId();

        return this.studentService.get(id);

    }

    @PutMapping("/update")
    public CreateStudentResponse update(@RequestBody UpdateStudentRequest updateStudentRequest){

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User user = (User) authentication.getPrincipal();
        Integer id = user.getStudent().getId();
        return this.studentService.update(updateStudentRequest.toStudent(),id);

    }

    @DeleteMapping("/delete")
    public  CreateStudentResponse delete(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User user = (User) authentication.getPrincipal();
        Integer id = user.getStudent().getId();
        return this.studentService.delete(id);
    }


}
