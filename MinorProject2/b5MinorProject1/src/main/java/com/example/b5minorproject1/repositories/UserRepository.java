package com.example.b5minorproject1.repositories;

import com.example.b5minorproject1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
