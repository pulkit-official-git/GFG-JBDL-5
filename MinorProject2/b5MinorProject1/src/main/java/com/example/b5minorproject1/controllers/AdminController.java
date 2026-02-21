package com.example.b5minorproject1.controllers;

import com.example.b5minorproject1.dtos.CreateAdminDto;
import com.example.b5minorproject1.models.Admin;
import com.example.b5minorproject1.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/create")
    public Integer createAdmin(@RequestBody CreateAdminDto createAdminDto) {
        return this.adminService.create(createAdminDto.toAdmin());

    }



}
