package com.example.b5minorproject1.repositories;

import com.example.b5minorproject1.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
