package com.example.b5minorproject1.services;

import com.example.b5minorproject1.dtos.CreateStudentResponse;
import com.example.b5minorproject1.models.*;
import com.example.b5minorproject1.repositories.TransactionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Ref;
import java.util.Date;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class TestTransactionService {

    @InjectMocks
    TransactionService transactionService;

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    BookService bookService;

    @Mock
    StudentService studentService;


    @Test
    public void testFine(){

//        just a basic demonstration of test cases
//        TransactionService transactionService = new TransactionService();
//
//        int expectedSum = transactionService.add(2,3);
//
//        Assert.assertEquals(6,expectedSum);

        Book book = Book.builder()
                .id(1)
                .bookName("Book 1")
                .build();

        Student student = Student.builder()
                .id(1)
                .studentName("Student 1")
                .build();

        Transaction transaction = Transaction.builder()
                .id(1)
                .book(book)
                .student(student)
                .transactionType(TransactionType.ISSUANCE)
                .transactionStatus(TransactionStatus.SUCCESS)
                .updatedOn(new Date(1737779337000L))
                .build();


        Mockito.when(transactionRepository.findTopByBookAndStudentAndTransactionTypeAndTransactionStatusOrderByIdDesc(
                Mockito.eq(book),
                        Mockito.eq(student),
                        Mockito.eq(TransactionType.ISSUANCE),
                        Mockito.eq(TransactionStatus.SUCCESS)
                ))
                        .thenReturn(transaction);

        ReflectionTestUtils.setField(transactionService,"studentDaysLimit",15);
        ReflectionTestUtils.setField(transactionService,"finePerDay",10);


        int expectedFine = transactionService.calculateFine(book,student);
        int actualFine = 3500;

        Assert.assertEquals(expectedFine,actualFine);


    }

    @Test
    public void testFineWithNoFine(){

//        just a basic demonstration of test cases
//        TransactionService transactionService = new TransactionService();
//
//        int expectedSum = transactionService.add(2,3);
//
//        Assert.assertEquals(6,expectedSum);



        Student student = Student.builder()
                .id(1)
                .studentName("Student 1")
                .build();

        Book book = Book.builder()
                .id(1)
                .bookName("Book 1")
                .student(student)
                .build();

        Transaction transaction = Transaction.builder()
                .id(1)
                .book(book)
                .student(student)
                .transactionType(TransactionType.ISSUANCE)
                .transactionStatus(TransactionStatus.SUCCESS)
                .updatedOn(new Date(1769315566000L))
                .build();


        Mockito.when(transactionRepository.findTopByBookAndStudentAndTransactionTypeAndTransactionStatusOrderByIdDesc(
                        Mockito.eq(book),
                        Mockito.eq(student),
                        Mockito.eq(TransactionType.ISSUANCE),
                        Mockito.eq(TransactionStatus.SUCCESS)
                ))
                .thenReturn(transaction);

        ReflectionTestUtils.setField(transactionService,"studentDaysLimit",15);
        ReflectionTestUtils.setField(transactionService,"finePerDay",10);


        int expectedFine = transactionService.calculateFine(book,student);
        int actualFine = 0;

        Assert.assertEquals(expectedFine,actualFine);

    }

    @Test
    public void TestInitiateReturn() throws Exception {


        Student student = Student.builder()
                .id(1)
                .studentName("Student 1")
                .build();

        Book book = Book.builder()
                .id(1)
                .bookName("Book 1")
                .student(student)
                .build();

        CreateStudentResponse createStudentResponse = CreateStudentResponse.builder()
                .student(student)
                .build();

        Mockito.when(bookService.get(Mockito.eq(1))).thenReturn(book);
        Mockito.when(studentService.get(Mockito.eq(1))).thenReturn(createStudentResponse);

        String expectedTxnId = UUID.randomUUID().toString();

        Transaction transaction = Transaction.builder()
                .id(1)
                .book(book)
                .student(student)
                .transactionType(TransactionType.ISSUANCE)
                .transactionStatus(TransactionStatus.SUCCESS)
                .updatedOn(new Date(1769315566000L))
                .txnId(expectedTxnId)
                .build();

        Mockito.when(transactionRepository.findTopByBookAndStudentAndTransactionTypeAndTransactionStatusOrderByIdDesc(
                        Mockito.eq(book),
                        Mockito.eq(student),
                        Mockito.eq(TransactionType.ISSUANCE),
                        Mockito.eq(TransactionStatus.SUCCESS)
                ))
                .thenReturn(transaction);

        ReflectionTestUtils.setField(transactionService,"studentDaysLimit",15);
        ReflectionTestUtils.setField(transactionService,"finePerDay",10);

        Mockito.when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(transaction);
        Mockito.when(bookService.save(Mockito.any(Book.class))).thenReturn(book);

        String actualTxnId = transactionService.initiateReturn(1,1);

        Assert.assertEquals(expectedTxnId,actualTxnId);
    }


    @Test(expected = Exception.class)
    public void TestInitiateReturnWithException() throws Exception {


        Student student = Student.builder()
                .id(1)
                .studentName("Student 1")
                .build();

        Book book = Book.builder()
                .id(1)
                .bookName("Book 1")
                .build();

        CreateStudentResponse createStudentResponse = CreateStudentResponse.builder()
                .student(student)
                .build();

        Mockito.when(bookService.get(Mockito.eq(1))).thenReturn(book);
        Mockito.when(studentService.get(Mockito.eq(1))).thenReturn(createStudentResponse);

        String actualTxnId = transactionService.initiateReturn(1, 1);
    }

    }
