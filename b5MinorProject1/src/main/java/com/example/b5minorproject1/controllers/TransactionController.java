package com.example.b5minorproject1.controllers;


import com.example.b5minorproject1.dtos.CreateStudentRequest;
import com.example.b5minorproject1.dtos.CreateStudentResponse;
import com.example.b5minorproject1.models.TransactionType;
import com.example.b5minorproject1.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/initiate")
    public String initiate(@RequestParam Integer studentId,
                           @RequestParam Integer bookId,
                           @RequestParam TransactionType transactionType) throws Exception {

        return this.transactionService.initiate(studentId,bookId,transactionType);
    }


}
