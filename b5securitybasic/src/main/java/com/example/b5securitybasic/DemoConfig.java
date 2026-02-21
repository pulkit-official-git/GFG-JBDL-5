package com.example.b5securitybasic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        UserDetails user1 = User.builder()
                .username("posty")
                .password(passwordEncoder().encode("posty@123"))
                .authorities("ADMIN")
                .build();

        UserDetails user2 = User.builder()
                .username("fred")
                .password(passwordEncoder().encode("fred@123"))
                .authorities("USER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/user/**").hasAuthority("USER")
//                                .requestMatchers()
//                        .requestMatchers("")
                        .anyRequest().permitAll()           // this is making an api non secure
//                        as by default every api in spring security is secure
                )
                .formLogin(Customizer.withDefaults());


        return http.build();
    }


}
