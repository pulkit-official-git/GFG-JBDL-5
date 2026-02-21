package com.example.b5minorproject1.services;

import com.example.b5minorproject1.models.*;
import com.example.b5minorproject1.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private BookService bookService;

    @Value("${library.student.bookLimit}")
    private int studentBookLimit;

    @Value("${library.day.limit}")
    private int studentDayLimit;

    @Value("${fine.per.day}")
    private int finePerDay;

    public String initiate(Integer studentId, Integer bookId, TransactionType transactionType) throws Exception {

        switch (transactionType){
            case ISSUANCE:
                return initiateIssuance(studentId, bookId);
            case RETURN:
                return initiateReturn(studentId, bookId);
                default:
                    throw new Exception("Invalid txnType");
        }

    }


    /*
    * Issuance
    *
    * 1.Data Retrieval
    *   a. getBook
    *   b. getStudent
    *
    * 2.Add validations
    *
    *   a.if student or book is null
    *   b.if book is allotted to some1else
    *   c.if limit of a student exceeds
    *
    * 3. Create a txn with status as pending
    *
    * 4. Allot the book to the student
    *
    * 5. make the transaction as success
    *
    * 6.if transaction is failed, make is as failed and handle accordingly
    *
    *
    *
    *
    *
    *
    * */



    private String initiateIssuance(Integer studentId, Integer bookId) throws Exception {

//        ######### DATA RETRIEVAL #########

        Student student = this.studentService.get(studentId).getStudent();
        Book book = this.bookService.get(bookId);

//        ####### VALIDATIONS ##########

        if(student == null || student.getStatus()== StudentStatus.INACTIVE){
            throw new Exception("invalid student");
        }
        if(book == null || book.getStudent()!=null){
            throw new Exception("invalid book or assigned to some1 else");
        }
        List<Book>issuedBooks = student.getBooks();//importance of bi directional mapping
        if(issuedBooks.size() >= studentBookLimit){
            throw new Exception("student book issue limit exceeded");
        }

        Transaction transaction = Transaction.builder()
                .student(student)
                .book(book)
                .transactionType(TransactionType.ISSUANCE)
                .transactionStatus(TransactionStatus.PENDING)
                .txnId(UUID.randomUUID().toString())
                .build();

        Transaction savedTransaction = this.transactionRepository.save(transaction);

        try{
            book.setStudent(student);
            this.bookService.save(book);//assign the book

            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            this.transactionRepository.save(transaction);

        }catch (Exception ex){

            transaction.setTransactionStatus(TransactionStatus.FAILED);
            this.transactionRepository.save(transaction);

            if(book.getStudent()!=null){
                book.setStudent(null);
            }
            this.bookService.save(book);

        }

        return transaction.getTxnId();
    }

    public String initiateReturn(Integer studentId, Integer bookId) throws Exception {

        Student student = this.studentService.get(studentId).getStudent();
        Book book = this.bookService.get(bookId);

//        ####### VALIDATIONS ##########

        if(student == null || student.getStatus()== StudentStatus.INACTIVE){
            throw new Exception("invalid student");
        }
        if(book == null || book.getStudent()==null || book.getStudent().getId()!=studentId){
            throw new Exception("invalid book or assigned to some1 else");
        }



        Transaction transaction = Transaction.builder()
                .student(student)
                .book(book)
                .transactionType(TransactionType.RETURN)
                .transactionStatus(TransactionStatus.PENDING)
                .txnId(UUID.randomUUID().toString())
                .build();

        Integer fine = this.calculateFine(book,student);
        transaction.setFine(fine);

        Transaction savedTransaction = this.transactionRepository.save(transaction);

        try{
            book.setStudent(null);
            this.bookService.save(book);//unassign the book

            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            this.transactionRepository.save(transaction);

        }catch (Exception ex){

            transaction.setTransactionStatus(TransactionStatus.FAILED);
            this.transactionRepository.save(transaction);

            if(book.getStudent()!=null){
                book.setStudent(student);
            }
            this.bookService.save(book);

        }


        return savedTransaction.getTxnId();
    }

    Integer calculateFine(Book book, Student student){

        Transaction issuedTxn = this.transactionRepository.findTopByBookAndStudentAndTransactionTypeAndTransactionStatusOrderByIdDesc(
                book,student,TransactionType.ISSUANCE,TransactionStatus.SUCCESS
        );

        Long timeInMillis = issuedTxn.getUpdatedOn().getTime();

        Long timeDiff =  System.currentTimeMillis() - timeInMillis ;


        Long daysPassed = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);

        if(daysPassed>studentDayLimit){
            return (daysPassed.intValue()-studentDayLimit)*finePerDay;
        }
        return 0;


    }

    public int add(int a,int b){
        return a+b+1;
    }
}
