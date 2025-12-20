package com.example.b4LombokValidation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class B4LombokValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(B4LombokValidationApplication.class, args);

		Book book = new Book("JK Rowling","harry potter","jk@Gmail",1,new Date(),new Date());
		System.out.println(book);

//		Book newBook = new Book();
//		newBook.setAuthor("JK Rowling")
//				.setTitle("harry potter")
//				.setAuthorEmail("jk@Gmail");
//			njcjw


//		newBook.setUpdatedOn(new Date());
//		System.out.println(newBook);
//		newBook.setId(3);
//		System.out.println(newBook);

		Book.BookBuilder b2 = Book.builder()
				.id(1)
				.title("JK Rowling")
				.author("harry potter");
//				.build();
		System.out.println(b2);
//		b2.setAuthorEmail("harry potter");

		b2.createdOn(new Date())
		.updatedOn(new Date()).build();
		System.out.println(b2);


	}

}
