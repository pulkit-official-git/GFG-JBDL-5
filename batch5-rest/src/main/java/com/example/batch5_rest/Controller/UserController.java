package com.example.batch5_rest.Controller;

import com.example.batch5_rest.Dtos.CreateUserResponse;
import com.example.batch5_rest.Dtos.PatchUserRequest;
import com.example.batch5_rest.Dtos.PutUserRequest;
import com.example.batch5_rest.Dtos.UserCreateRequest;
import com.example.batch5_rest.Models.User;
import com.example.batch5_rest.Services.UserService;
import org.springframework.web.bind.annotation.*;

//controller -> endpoints,validations


@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController() {
        this.userService = new  UserService();
    }

    @PostMapping("/create2")//way1
    public CreateUserResponse createUser2(@RequestBody UserCreateRequest userCreateRequest){
//        UserService userService = new UserService();
         return CreateUserResponse.fromModel(userService.createUser(userCreateRequest));
    }

    @PostMapping("/create")//way2
    public Integer createUser(@RequestBody UserCreateRequest userCreateRequest){
//        UserService userService = new UserService();
        return userService.createUser(userCreateRequest).getId();
    }

    @GetMapping("/get")
    public User getUser(@RequestParam Integer id){
//        UserService userService = new UserService();
        return userService.getUser(id);
    }

    @GetMapping("/get/userid/{userId}")
    public User getUserPath(@PathVariable("userId") Integer id){
//        UserService userService = new UserService();
        return userService.getUser(id);
    }


    @PutMapping("/update")
    public User updateUser(@RequestBody PutUserRequest putUserRequest,
                           @RequestParam Integer id){
        return this.userService.putUpdate(id,putUserRequest.toUser());
    }

    public User patchUser(@RequestBody PatchUserRequest patchUserRequest,
                          @RequestParam Integer id){
        return this.userService.patchUpdate(patchUserRequest.toUser(),id);
    }


}
