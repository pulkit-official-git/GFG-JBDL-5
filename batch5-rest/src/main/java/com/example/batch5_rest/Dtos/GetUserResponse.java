package com.example.batch5_rest.Dtos;

import com.example.batch5_rest.Models.Gender;
import com.example.batch5_rest.Models.User;

public class GetUserResponse {


    private Integer id;
    private String name;
    private String email;
    private Integer age;
    private Gender gender;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public static GetUserResponse fromModel(User user){
        GetUserResponse getUserResponse = new GetUserResponse();
        if(user == null){
            return null;
        }
        getUserResponse.setId(user.getId());
        getUserResponse.setName(user.getName());
        getUserResponse.setEmail(user.getEmail());
        getUserResponse.setAge(user.getAge());
        getUserResponse.setGender(user.getGender());
        return getUserResponse;
    }
}
