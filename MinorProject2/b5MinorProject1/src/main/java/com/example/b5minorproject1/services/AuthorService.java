package com.example.b5minorproject1.services;

import com.example.b5minorproject1.models.Author;
import com.example.b5minorproject1.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author save(Author author) {
        return this.authorRepository.save(author);
    }
}
