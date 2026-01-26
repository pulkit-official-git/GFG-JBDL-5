package com.example.b5minorproject1.services;

import com.example.b5minorproject1.models.Author;
import com.example.b5minorproject1.models.Book;
import com.example.b5minorproject1.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    public Book create(Book book) {

        Author author = book.getAuthor();
        author = this.authorService.save(author);
        book.setAuthor(author);
        return this.bookRepository.save(book);
    }

    public Book get(Integer id) {
        return this.bookRepository.findById(id).orElse(null);
    }

    public Book save(Book book) {
        return this.bookRepository.save(book);
    }
}
