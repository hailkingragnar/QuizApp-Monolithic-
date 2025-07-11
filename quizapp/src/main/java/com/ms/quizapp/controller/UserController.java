package com.ms.quizapp.controller;

import com.ms.quizapp.model.Users;
import com.ms.quizapp.service.UsersService;
import com.ms.quizapp.wrapper.ApiWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("User")
public class UserController {
    @Autowired
    private UsersService usersService;
    @PostMapping("register")
    public ResponseEntity<ApiWrapper<String>> register(@RequestBody Users users){
       boolean status = usersService.registerUser(users);
       if(status){
           return ResponseEntity.ok(new ApiWrapper<>("User saved Successfully","Success",true));
       } else {
           return ResponseEntity.ok(new ApiWrapper<>("Error saving user","failed",false));

       }

    }

}
