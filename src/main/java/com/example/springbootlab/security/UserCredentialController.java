package com.example.springbootlab.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserCredentialController {

    private PasswordEncoder encoder;
    private UserCredentialsRepository repository;

    public UserCredentialController (PasswordEncoder encoder, UserCredentialsRepository repository){
        this.encoder = encoder;
        this.repository = repository;
    }
    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto){
        UserCredentials user = new UserCredentials();
        user.setName(userDto.getName());
        user.setPassword(encoder.encode(userDto.getPassword()));

        if (repository.findByName(user.getName()) != null)
            ResponseEntity.status(HttpStatus.CONFLICT).build();

        repository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
