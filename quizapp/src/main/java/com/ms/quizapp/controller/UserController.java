package com.ms.quizapp.controller;

import com.ms.quizapp.dto.UserLogin;
import com.ms.quizapp.dto.UserRegistration;
import com.ms.quizapp.dto.UserResponse;
import com.ms.quizapp.model.Users;
import com.ms.quizapp.service.JWTService;
import com.ms.quizapp.service.UsersService;
import com.ms.quizapp.wrapper.ApiWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsersService usersService;
    @Autowired
    private JWTService jwtService;



    @PostMapping("register")
    public ResponseEntity<ApiWrapper<String>> register(@RequestBody UserRegistration users){
       boolean status = usersService.registerUser(users);
       if(status){
           return ResponseEntity.ok(new ApiWrapper<>("User saved Successfully","Success",true));
       } else {
           return ResponseEntity.ok(new ApiWrapper<>("Error saving user","failed",false));

       }

    }
    @RequestMapping("/")
    public String home(){
        return "Welcome to quizapp Home";
    }


    @RequestMapping("api/v1/authenticate")
    public ResponseEntity<ApiWrapper<String>> login(@RequestBody UserLogin user){
           Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserEmail(),user.getUserPassword()));

           if(authentication.isAuthenticated()) {
               String key =  jwtService.generateToken(user.getUserEmail());
               return ResponseEntity.ok(new ApiWrapper<>(key,"Success",true));
           } else {
               return ResponseEntity.ok(new ApiWrapper<>("failed",false));
           }
    }
}
