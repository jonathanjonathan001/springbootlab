package com.example.springbootlab.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private PasswordEncoder passwordEncoder;

    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain filterChainForRestApi(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .securityMatcher("/api/**")
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name())
                .requestMatchers(HttpMethod.POST, "/api/*").hasRole(UserRole.ADMIN.name())
                .requestMatchers(HttpMethod.PUT, "/api/**").hasRole(UserRole.ADMIN.name())
                .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole(UserRole.ADMIN.name())
                .anyRequest().denyAll()
                .and()
                .httpBasic()
                .and()
                .build();
    }


    @Bean
    @Order(2)
    public SecurityFilterChain filterChainForWeb(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests()
                .requestMatchers("/showmovies").authenticated()
                .anyRequest().denyAll()
                .and()
                .formLogin()
                .defaultSuccessUrl("/showmovies")
                .and();

        return httpSecurity.build();
    }


    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder.encode("user")).roles(UserRole.USER.name())
                .and()
                .withUser("admin").password(passwordEncoder.encode("admin")).roles(UserRole.ADMIN.name());
    }


}
