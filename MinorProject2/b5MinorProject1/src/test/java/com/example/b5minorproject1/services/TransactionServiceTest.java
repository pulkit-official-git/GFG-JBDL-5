package com.example.b5minorproject1.services;

import com.example.b5minorproject1.dtos.CreateStudentResponse;
import com.example.b5minorproject1.models.*;
import com.example.b5minorproject1.repositories.BookRepository;
import com.example.b5minorproject1.repositories.StudentRepository;
import com.example.b5minorproject1.repositories.TransactionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private BookService bookService;

    @Mock
    private StudentService studentService;



    @Test
    public void TestFine(){

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
                .transactionStatus(TransactionStatus.SUCCESS)
                .transactionType(TransactionType.ISSUANCE)
                .updatedOn(new Date(1737772408000L))
                .build();

        Mockito.when(transactionRepository.findTopByBookAndStudentAndTransactionTypeAndTransactionStatusOrderByIdDesc(
                Mockito.eq(book),Mockito.eq(student),Mockito.eq(TransactionType.ISSUANCE),Mockito.eq(TransactionStatus.SUCCESS)
                )
                ).thenReturn(transaction);

        ReflectionTestUtils.setField(transactionService,"studentDayLimit",15);
        ReflectionTestUtils.setField(transactionService,"finePerDay",10);

        int result = transactionService.calculateFine(book,student);
        Assert.assertEquals(result,3500);
//        TransactionService transactionService = new TransactionService();
//
//        int a = transactionService.add(1,2);
//
//        Assert.assertEquals(4,a);

    }

    @Test
    public void TestFineWithNoFine(){

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
                .transactionStatus(TransactionStatus.SUCCESS)
                .transactionType(TransactionType.ISSUANCE)
                .updatedOn(new Date(1769308821000L))
                .build();

        Mockito.when(transactionRepository.findTopByBookAndStudentAndTransactionTypeAndTransactionStatusOrderByIdDesc(
                        Mockito.eq(book),Mockito.eq(student),Mockito.eq(TransactionType.ISSUANCE),Mockito.eq(TransactionStatus.SUCCESS)
                )
        ).thenReturn(transaction);

        ReflectionTestUtils.setField(transactionService,"studentDayLimit",15);
        ReflectionTestUtils.setField(transactionService,"finePerDay",10);

        int result = transactionService.calculateFine(book,student);
        Assert.assertEquals(result,0);
//        TransactionService transactionService = new TransactionService();
//
//        int a = transactionService.add(1,2);
//
//        Assert.assertEquals(4,a);

    }

    @Test(expected = Exception.class)
    public void TestInitiateRefundWithException() throws Exception {

        Book book = Book.builder()
                .id(1)
                .bookName("Book 1")
                .build();

        Student student = Student.builder()
                .id(1)
                .studentName("Student 1")
                .build();

        CreateStudentResponse createStudentResponse =  CreateStudentResponse.builder()
                .student(student)
                .build();

        Mockito.when(bookService.get(Mockito.eq(1))).thenReturn(book);
        Mockito.when(studentService.get(Mockito.eq(1))).thenReturn(createStudentResponse);

        transactionService.initiateReturn(1,1);


    }


    @Test
    public void TestInitiateRefundWithoutException() throws Exception {



        Student student = Student.builder()
                .id(1)
                .studentName("Student 1")
                .build();

        Book book = Book.builder()
                .id(1)
                .bookName("Book 1")
                .student(student)
                .build();

        CreateStudentResponse createStudentResponse =  CreateStudentResponse.builder()
                .student(student)
                .build();

        Mockito.when(bookService.get(Mockito.eq(1))).thenReturn(book);
        Mockito.when(studentService.get(Mockito.eq(1))).thenReturn(createStudentResponse);

        String txnId = UUID.randomUUID().toString();
        Transaction transaction = Transaction.builder()
                .id(1)
                .book(book)
                .student(student)
                .transactionStatus(TransactionStatus.SUCCESS)
                .transactionType(TransactionType.ISSUANCE)
                .updatedOn(new Date(1769308821000L))
                .txnId(txnId)
                .build();

        Mockito.when(transactionRepository.findTopByBookAndStudentAndTransactionTypeAndTransactionStatusOrderByIdDesc(
                        Mockito.eq(book),Mockito.eq(student),Mockito.eq(TransactionType.ISSUANCE),Mockito.eq(TransactionStatus.SUCCESS)
                )
        ).thenReturn(transaction);

        ReflectionTestUtils.setField(transactionService,"studentDayLimit",15);
        ReflectionTestUtils.setField(transactionService,"finePerDay",10);





        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);
        Mockito.when(bookService.save(Mockito.any(Book.class))).thenReturn(book);





        String returnedTxnId = transactionService.initiateReturn(1,1);

        Assert.assertEquals(returnedTxnId,txnId);


    }
}
