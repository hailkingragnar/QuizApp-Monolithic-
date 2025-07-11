package com.ms.quizapp.service;

import com.ms.quizapp.model.Users;
import com.ms.quizapp.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsersRepo usersRepo;

    public boolean registerUser(Users users) {

       try {
           Users users1 = new Users();
           users1.setEmail(users.getEmail());

           users1.setPassword(passwordEncoder.encode(users.getPassword()));
           users1.setRole(users.getRole());
           usersRepo.save(users1);
           return true;
       } catch(Exception e){
           return false;
       }

    }


}
