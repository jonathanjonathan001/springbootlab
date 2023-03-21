package com.example.springbootlab.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DatabaseUserDetailService implements UserDetailsService {

    private UserCredentialsRepository repo;

    public DatabaseUserDetailService(UserCredentialsRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserCredentials userCredentials = repo.findByName(username);
        if (userCredentials == null)
            throw new UsernameNotFoundException("Username not found");

        UserDetails userDetails = new User(userCredentials.getName(),userCredentials.getPassword(),Set.of());

        return userDetails;
    }
}
