package com.example.b5minorproject1.controllers;


import com.example.b5minorproject1.dtos.CreateBookRequest;
import com.example.b5minorproject1.models.Book;
import com.example.b5minorproject1.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    public Book createBook(@RequestBody CreateBookRequest createBookRequest) {
        return this.bookService.create(createBookRequest.toBook());

    }

    @GetMapping("/get")
    public Book getBookById(@RequestParam Integer id) {
        return this.bookService.get(id);
    }
}
