package com.example.springbootlab.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

@Bean
public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
    httpSecurity
            .csrf()
            .ignoringRequestMatchers("/register")
            .and()
            .formLogin()
            .and()
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.POST, "/register").permitAll()
            .requestMatchers("/movies").authenticated()
            .anyRequest().denyAll();

    return httpSecurity.build();

}
@Bean
public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}

}
