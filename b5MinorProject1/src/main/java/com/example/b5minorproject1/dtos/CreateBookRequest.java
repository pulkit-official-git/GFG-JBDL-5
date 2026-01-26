package com.example.b5minorproject1.dtos;

import com.example.b5minorproject1.models.Author;
import com.example.b5minorproject1.models.Book;
import com.example.b5minorproject1.models.Genre;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookRequest {

    private String bookName;

    private Genre genre;

    private String authorName;

    private String authorEmail;

    public Book toBook() {
        return Book.builder()
                .bookName(bookName)
                .genre(genre)
                .author(
                        Author.builder()
                                .authorName(authorName)
                                .authorEmail(authorEmail)
                                .build()
                )
                .build();
    }

}
