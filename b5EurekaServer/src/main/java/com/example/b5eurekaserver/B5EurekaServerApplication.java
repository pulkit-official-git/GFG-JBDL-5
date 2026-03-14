package com.example.b5eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class B5EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(B5EurekaServerApplication.class, args);
    }

}
