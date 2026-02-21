package com.example.B5InDbSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    DemoUserDetailsService demoUserDetailsService;



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

    @PostMapping("/user/signup")
    public void userSignup(@RequestParam String username, @RequestParam String password) {
        DemoUser demoUser = DemoUser.builder()
                .username(username)
                .password(password)
                .authorities("USER")
                .build();

        this.demoUserDetailsService.create(demoUser);
    }

    @PostMapping("/admin/signup")
    public void adminSignup(@RequestParam String username, @RequestParam String password) {
        DemoUser demoUser = DemoUser.builder()
                .username(username)
                .password(password)
                .authorities("ADMIN")
                .build();

        this.demoUserDetailsService.create(demoUser);
    }
}
