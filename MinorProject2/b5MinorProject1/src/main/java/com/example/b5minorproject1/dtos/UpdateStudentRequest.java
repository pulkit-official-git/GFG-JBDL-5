package com.example.b5minorproject1.dtos;

import com.example.b5minorproject1.models.Gender;
import com.example.b5minorproject1.models.Student;
import com.example.b5minorproject1.models.StudentStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateStudentRequest {


    private String studentName;

    private String studentEmail;

    private StudentStatus status=StudentStatus.ACTIVE;

    private Gender gender;

    public Student toStudent(){
        return Student.builder()
                .studentName(studentName)
                .studentEmail(studentEmail)
                .gender(gender)
                .status(status)
                .build();
    }
}
