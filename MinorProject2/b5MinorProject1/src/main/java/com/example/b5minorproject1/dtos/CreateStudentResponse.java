package com.example.b5minorproject1.dtos;

import com.example.b5minorproject1.models.Student;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStudentResponse {

    private Student student;

//    private Integer id;
//
//    private Date createdOn;
}
