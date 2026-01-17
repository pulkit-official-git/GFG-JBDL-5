package com.example.b5jpa.repository;

import com.example.b5jpa.models.Book;
import com.example.b5jpa.models.Genre;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "select * from book b where b.id = :id",nativeQuery = true)// t2 time as no conversion needed
    Book getbyidNative(Integer id);

    @Query("select b from Book b where b.id = ?1")//t1 + t2 time
    Book getbyidJpql(Integer id);

    @Query("select b from Book b where b.id = :id")
    Book getbyidJpql2(Integer id);


    @Query("select b from Book b where b.genre=?1 and b.name=?2")
    Book getByGenreAndNamejpql(String genre, String name);

    @Query("select b from Book b where b.genre=:genre and b.name=:name")
    Book getByGenreAndNameJpql2(String genre, String name);

    @Query(value = "select * from book where genre = :genre and name = :name",nativeQuery = true)
    Book getByGenreAndNameNative(String genre, String name);


    Book findByGenreAndName(Genre genre, String name);

    @Transactional //atomicity
    @Modifying
    @Query("update Book b set b.genre = ?2 where b.id=?1")
    void updateGenreById(Integer id, Genre genre);
}
