package com.example.b5jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public void createBook(Book book) {

        try {
            this.bookRepository.createBook(book);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Book> getAll() throws SQLException {

        return this.bookRepository.getAll();
    }

    public Book getBook(Integer id) throws SQLException {
        return this.bookRepository.getBook(id);
    }
}
