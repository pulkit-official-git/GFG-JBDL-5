package com.example.b5minorproject1.configs;


import com.example.b5minorproject1.models.Authority;
import com.example.b5minorproject1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private UserService userService;

    @Bean
    public AuthenticationManager authenticationManager(UserService userService,
                                                       PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/student/admin/**").hasAuthority(Authority.ADMIN.name())
                        .requestMatchers("/book/create").hasAuthority(Authority.ADMIN.name())
                        .requestMatchers("/book/**").hasAuthority(Authority.STUDENT.name())
                        .requestMatchers("/admin/**").hasAuthority(Authority.ADMIN.name())
                        .requestMatchers("/student/**").hasAuthority(Authority.STUDENT.name())
                        .requestMatchers("/student/create").permitAll()
                )

                .httpBasic(Customizer.withDefaults());


        return http.build();
    }


}
