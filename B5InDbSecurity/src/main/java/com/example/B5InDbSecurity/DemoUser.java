package com.example.B5InDbSecurity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class DemoUser implements UserDetails {

    private static final String DELIMITER = "::";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){

        String[] authorities = this.authorities.split(DELIMITER);

        return Arrays.stream(authorities)
                .map(a->new SimpleGrantedAuthority(a))
                .collect(Collectors.toList());


    }





}
