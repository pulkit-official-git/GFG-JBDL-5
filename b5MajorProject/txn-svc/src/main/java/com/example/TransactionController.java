package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/txn")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/initiate")
    public String initiate(@RequestParam Long sender, @RequestParam Long receiver,
                           @RequestParam Long amount) {
        return this.transactionService.initiate(sender,receiver,amount);

    }
}
