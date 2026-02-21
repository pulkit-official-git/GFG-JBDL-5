package com.example.b5minorproject1.dtos;

import com.example.b5minorproject1.models.Gender;
import com.example.b5minorproject1.models.Student;
import com.example.b5minorproject1.models.StudentStatus;
import com.example.b5minorproject1.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStudentRequest {

    private String username;

    private String password;


    @NotBlank
    private String studentName;

    @Email
    private String studentEmail;

    @NonNull
    private Gender gender;

    public Student toStudent(){
        return Student.builder()
                .studentName(studentName)
                .studentEmail(studentEmail)
                .gender(gender)
                .status(StudentStatus.ACTIVE)
                .user(User.builder().
                        username(username).
                        password(password).
                        build())
                .build();
    }
}
