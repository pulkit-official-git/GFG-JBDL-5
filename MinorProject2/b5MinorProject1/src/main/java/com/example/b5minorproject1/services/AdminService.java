package com.example.b5minorproject1.services;

import com.example.b5minorproject1.models.Admin;
import com.example.b5minorproject1.models.Authority;
import com.example.b5minorproject1.models.User;
import com.example.b5minorproject1.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserService userService;

    public Integer create(Admin admin) {
        User user = admin.getUser();
        user = this.userService.createUser(user, Authority.ADMIN);
        admin.setUser(user);
        this.adminRepository.save(admin);
        return admin.getId();
    }
}
