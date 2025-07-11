package com.ms.quizapp.service;

import com.ms.quizapp.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean registerUser(Users users) {
        Users users1 = new Users();
        users1.setEmail(users.getEmail());
        users1.setRole(users.getRole());
        users1.setPassword(passwordEncoder.encode(users.getPassword()));

    }


}
