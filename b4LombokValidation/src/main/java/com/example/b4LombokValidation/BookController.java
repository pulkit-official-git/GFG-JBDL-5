package com.example.b4LombokValidation;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    BookService bookService;

    public BookController() {
        this.bookService = new BookService();
    }

    @PostMapping("/create")
    public Integer createBook(@Valid @RequestBody Book book) {
        return this.bookService.create(book);
    }
}
