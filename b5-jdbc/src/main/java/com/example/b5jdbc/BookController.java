package com.example.b5jdbc;


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
}

//task :- implement put patch and delete and pagination
//try to implement generic databaseRepository also
