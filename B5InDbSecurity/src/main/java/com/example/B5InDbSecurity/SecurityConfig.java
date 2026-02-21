package com.example.B5InDbSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
    PasswordEncoder passwordEncoder;

    @Autowired
    DemoUserDetailsService demoUserDetailsService;


    //Authentication
    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder,
                                                       DemoUserDetailsService demoUserDetailsService) {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(demoUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(daoAuthenticationProvider);
    }




//    Authorisation

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth->auth
                                .requestMatchers(HttpMethod.POST,"/user/signup").permitAll()
                                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                                .requestMatchers("/user/**").hasAuthority("USER")
                                .requestMatchers("/demo/**").hasAnyAuthority("ADMIN","USER")
//                                .requestMatchers()
//                        .requestMatchers("")
                                .anyRequest().permitAll()           // this is making an api non secure
//                        as by default every api in spring security is secure
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
