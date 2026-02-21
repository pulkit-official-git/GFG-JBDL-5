package com.example.b5securitybasic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/admin")
    public String admin() {
        return "Hello Admin";
    }

    @GetMapping("/user")
    public String user() {
        return "Hello User";
    }

    @GetMapping("/demo")
    public String demo() {
        return "Hello Demo";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello hello";
    }


}
