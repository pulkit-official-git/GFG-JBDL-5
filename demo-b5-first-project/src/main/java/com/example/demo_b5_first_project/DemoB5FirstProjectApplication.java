package com.example.demo_b5_first_project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoB5FirstProjectApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(DemoB5FirstProjectApplication.class);

	@Value("${library.no.of.books}")
	private int noOfBooks;

	public static void main(String[] args) {
		SpringApplication.run(DemoB5FirstProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World");
		System.out.println(this.noOfBooks);
		logger.warn("Number of books is  {}", this.noOfBooks);
	}

}

//tomcat -> apache
//jetty  -> eclipse
//undertow -> jboss
