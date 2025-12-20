package com.example.batch5_rest.Repositories;

import com.example.batch5_rest.Models.User;

import java.util.HashMap;
import java.util.Random;

public class UserRepository {

    HashMap<Integer, User> users = new HashMap<>();


    public User createUser(User user) {
        Integer userId = new Random().nextInt(100);
        user.setId(userId);
        users.put(userId, user);
        return user;
    }

    public User getUser(Integer id) {
        if(!users.containsKey(id)) {
            return null;
        }
        return users.get(id);
    }

    public User update(Integer id, User user) {
        if(!users.containsKey(id)) {
            return null;
        }
        user.setId(id);
        users.put(user.getId(), user);
        return user;
    }
}
