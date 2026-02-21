package com.example.b5oauth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/user")
    public String user(){
        return "Welcome to B5OAUTH";
    }
}
