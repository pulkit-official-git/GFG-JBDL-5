package com.example.b5minorproject1.repositories;

import com.example.b5minorproject1.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Transaction findTopByBookAndStudentAndTransactionTypeAndTransactionStatusOrderByIdDesc(Book book, Student student, TransactionType transactionType, TransactionStatus transactionStatus);
}
