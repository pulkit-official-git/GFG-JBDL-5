package com.example.b5minorproject1.repositories;

import com.example.b5minorproject1.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
