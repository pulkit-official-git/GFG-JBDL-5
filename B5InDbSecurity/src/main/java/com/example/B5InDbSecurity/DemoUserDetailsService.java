package com.example.B5InDbSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DemoUserDetailsService implements UserDetailsService {

    @Autowired
    private DemoUserRepository demoUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.demoUserRepository.findByUsername(username);
    }

    public void create(DemoUser demoUser) {
        demoUser.setPassword(passwordEncoder.encode(demoUser.getPassword()));
        this.demoUserRepository.save(demoUser);
    }
}
