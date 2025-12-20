package com.example.batch5_rest.Dtos;

import com.example.batch5_rest.Models.Gender;
import com.example.batch5_rest.Models.User;

import java.util.Date;

public class UserCreateRequest {


    private String name;
    private String email;
    private Integer age;
    private Gender gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    public User toModel(){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setAge(age);
        user.setGender(gender);
        user.setCreatedOn(new Date());
        user.setUpdatedOn(new Date());
        return user;
    }
}
