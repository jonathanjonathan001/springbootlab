package com.example.springbootlab.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

@Bean
public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
    httpSecurity.formLogin()
            .and()
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.GET,"/movies/**").authenticated()
            .anyRequest().denyAll();
    return httpSecurity.build();

}

}
