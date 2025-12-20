package com.example.b4LombokValidation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Book {
//    @Setter
    @NotBlank
    private String title;
//    @Setter
    @NotBlank
    private String author;
//    @Setter
    @Email
    private String authorEmail;
//    @Setter
//    @NonNull
    private Integer id;
    
    private Date  createdOn;
    private Date updatedOn;

//    public Book setTitle(String title) {
//        this.title = title;
//        return this;
//    }
//
//    public Book setAuthor(String author) {
//        this.author = author;
//        return this;
//    }
//
//    public Book setAuthorEmail(String authorEmail) {
//        this.authorEmail = authorEmail;
//        return this;
//    }
//
//    public Book setId(Integer id) {
//        this.id = id;
//        return this;
//    }
//
//    public Book setCreatedOn(Date createdOn) {
//        this.createdOn = createdOn;
//        return this;
//    }
//
//    public Book setUpdatedOn(Date updatedOn) {
//        this.updatedOn = updatedOn;
//        return this;
//    }
}
