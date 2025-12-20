package com.example.batch5_rest.Services;

import com.example.batch5_rest.Dtos.CreateUserResponse;
import com.example.batch5_rest.Dtos.GetUserResponse;
import com.example.batch5_rest.Dtos.UserCreateRequest;
import com.example.batch5_rest.Models.User;
import com.example.batch5_rest.Repositories.UserRepository;

public class UserService {

    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public User createUser(UserCreateRequest userCreateRequest) {
//        UserRepository userRepository = new UserRepository();
        User user = userCreateRequest.toModel();
        user = userRepository.createUser(user);
        return user;
    }

    public User getUser(Integer id) {
//        UserRepository userRepository = new UserRepository();
        User user = userRepository.getUser(id);
        return user;
//        return GetUserResponse.fromModel(user);

    }

    public User putUpdate(Integer id, User user) {

        User user1 = userRepository.update(id,user);
        return user1;

    }

    public User merge(User existingUser, User incomingUser) {

        if(incomingUser.getName()!=null){
            existingUser.setName(incomingUser.getName());
        }
        if(incomingUser.getEmail()!=null){
            existingUser.setEmail(incomingUser.getEmail());
        }
        if(incomingUser.getAge()!=null){
            existingUser.setAge(incomingUser.getAge());
        }
        if(incomingUser.getGender()!=null){
            existingUser.setGender(incomingUser.getGender());
        }
        return existingUser;
    }

    public User patchUpdate(User user, Integer id) {

        User existingUser = this.userRepository.getUser(id);
        User mergedUser = this.merge(user, existingUser);
        this.userRepository.update(id, mergedUser);
        return mergedUser;



    }
}
