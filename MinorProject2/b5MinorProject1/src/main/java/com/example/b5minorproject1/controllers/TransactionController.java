package com.example.b5minorproject1.controllers;


import com.example.b5minorproject1.dtos.CreateStudentRequest;
import com.example.b5minorproject1.dtos.CreateStudentResponse;
import com.example.b5minorproject1.models.TransactionType;
import com.example.b5minorproject1.models.User;
import com.example.b5minorproject1.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/initiate")
    public String initiate(@RequestParam Integer bookId,
                           @RequestParam TransactionType transactionType) throws Exception {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = (User) authentication.getPrincipal();

        Integer studentId = user.getStudent().getId();

        return this.transactionService.initiate(studentId,bookId,transactionType);
    }


}
