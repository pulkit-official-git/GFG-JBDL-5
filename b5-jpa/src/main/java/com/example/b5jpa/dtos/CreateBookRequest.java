package com.example.b5jpa.dtos;

import com.example.b5jpa.models.Book;
import com.example.b5jpa.models.Genre;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookRequest {

    @NotBlank
    @Length(min = 1, max = 100)
    private String name;

    @NotBlank
    private String author;

    @NotBlank
    private String authorEmail;

    private Genre genre;

    public Book toBook(){
        return Book.builder()
                .name(name)
                .author(author)
                .authorEmail(authorEmail)
                .genre(genre)
                .build();
    }
}
