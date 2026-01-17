package com.example.b5jdbc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {


    private Connection connection = null;

//    @Value("${db.url}")
    private String url;

//    @Value("${db.username}")
    private String username;

//    @Value("${db.password}")
    private String password;

    public BookRepository(@Value("${db.url}")String url,
                          @Value("${db.username}")String username,
                          @Value("${db.password}")String password) throws SQLException {
        this.url = url;
        this.username = username;
        this.password = password;
        createBookTable();
    }

//    @Autowired
//    private DatabaseRepository databaseRepository;

//    public BookRepository(DatabaseRepository databaseRepository) throws SQLException {
//        this.databaseRepository = databaseRepository;
//        createBookTable();
//    }


    public void createBook(Book book) throws SQLException {

        /*
        * tempname = book.getname();
        * author = book.getauthor();
        *  String sql = "insert into book(name,author,authorEmail,genre,createdOn,updatedOn)" +
                " values(tempname,author,?,?,?,?)";
        * */

        String sql = "insert into book(name,author,authorEmail,genre,createdOn,updatedOn)" +
                " values(?,?,?,?,?,?)";

        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setString(1,book.getName());
        preparedStatement.setString(2,book.getAuthor());
        preparedStatement.setString(3,book.getAuthorEmail());
        preparedStatement.setString(4,book.getGenre().name());
        preparedStatement.setDate(5,new Date(book.getCreatedOn().getTime()));
        preparedStatement.setDate(6,new Date(book.getUpdatedOn().getTime()));


        preparedStatement.execute();
    }

    private Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(this.url,this.username,this.password);
        }
        return connection;

    }

    private void createBookTable() throws SQLException {
        Connection connection = getConnection();
        String sql = "create table if not exists book(id int primary key auto_increment ,name varchar(100),author varchar(100)," +
                "authorEmail varchar(50),genre varchar(50),createdOn date,updatedOn date)";

        Statement statement = connection.createStatement();
        statement.execute(sql);

    }


    public List<Book> getAll() throws SQLException {

        String sql = "select * from book";

        Statement statement = getConnection().createStatement();

        List<Book> books = new ArrayList<>();

        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getInt(1));
            book.setAuthor(resultSet.getString("author"));
            book.setName(resultSet.getString("name"));
            book.setAuthorEmail(resultSet.getString("authorEmail"));
            book.setGenre(Genre.valueOf(resultSet.getString("genre")));
            book.setCreatedOn(resultSet.getDate("createdOn"));
            book.setUpdatedOn(resultSet.getDate("updatedOn"));
            books.add(book);
        }
        return books;

    }

    public Book getBook(Integer id) throws SQLException {

        String sql = "select * from book where id = ?";

        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Book book = new Book();
        while(resultSet.next()) {
            book.setId(resultSet.getInt(1));
            book.setAuthor(resultSet.getString("author"));
            book.setName(resultSet.getString("name"));
            book.setAuthorEmail(resultSet.getString("authorEmail"));
            book.setGenre(Genre.valueOf(resultSet.getString("genre")));
            book.setCreatedOn(resultSet.getDate("createdOn"));
            book.setUpdatedOn(resultSet.getDate("updatedOn"));
        }
        return book;

    }
}
