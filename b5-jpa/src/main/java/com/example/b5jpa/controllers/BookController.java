package com.example.b5jpa.controllers;


import com.example.b5jpa.dtos.CreateBookRequest;
import com.example.b5jpa.models.Book;
import com.example.b5jpa.models.Genre;
import com.example.b5jpa.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;


    @PostMapping("/create")
    public void createBook(@RequestBody CreateBookRequest createBookRequest) {

        this.bookService.createBook(createBookRequest.toBook());

    }

    @GetMapping("/getAll") //implement pagination
    public List<Book> getAllBook() throws SQLException {

        return this.bookService.getAll();
    }

    @GetMapping("/getBook")
    public Book getBookById(@RequestParam Integer id) throws SQLException {
        return this.bookService.getBook(id);
    }

    @GetMapping("/getByGenreAndNamme")
    public Book getByGenreAndNamme(@RequestParam Genre genre, @RequestParam String name) throws SQLException {
        return this.bookService.getBookByGenreAndName(genre,name);
    }

    @PutMapping("/updateGenreByid")
    public Book updateGenreById(@RequestParam Integer id, @RequestParam Genre genre) throws SQLException {
        return this.bookService.updateGenreById(id,genre);
    }
}

//task :- implement put patch and delete and pagination
//try to implement generic databaseRepository also
