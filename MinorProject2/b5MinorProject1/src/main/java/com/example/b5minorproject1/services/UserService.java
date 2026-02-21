package com.example.b5minorproject1.services;

import com.example.b5minorproject1.models.Authority;
import com.example.b5minorproject1.models.User;
import com.example.b5minorproject1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findById(username).orElse(null);
    }

    public User createUser(User user, Authority authority) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(authority);
        return userRepository.save(user);
    }
}
