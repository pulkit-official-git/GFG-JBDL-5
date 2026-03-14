package com.example.b5notifsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class B5NotifSvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(B5NotifSvcApplication.class, args);
    }

}
