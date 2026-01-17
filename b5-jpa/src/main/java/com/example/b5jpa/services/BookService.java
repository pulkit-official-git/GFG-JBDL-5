package com.example.b5jpa.services;

import com.example.b5jpa.models.Book;
import com.example.b5jpa.models.Genre;
import com.example.b5jpa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public void createBook(Book book) {

        this.bookRepository.save(book);

    }

    public List<Book> getAll() throws SQLException {

        return this.bookRepository.findAll();
    }

    public Book getBook(Integer id) {
//        return this.bookRepository.findById(id).orElse(null);
        return this.bookRepository.getbyidJpql(id);
    }

    public Book getBookByGenreAndName(Genre genre, String name) {
        return this.bookRepository.findByGenreAndName(genre,name);
    }

    public Book updateGenreById(Integer id, Genre genre) {
        this.bookRepository.updateGenreById(id,genre);
        return getBook(id);
    }
}
