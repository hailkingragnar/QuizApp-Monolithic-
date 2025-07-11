package com.ms.quizapp.service;

import com.ms.quizapp.RoleType;
import com.ms.quizapp.dto.UserRegistration;
import com.ms.quizapp.dto.UserResponse;
import com.ms.quizapp.model.UserRoles;
import com.ms.quizapp.model.Users;
import com.ms.quizapp.repo.RolesRepo;
import com.ms.quizapp.repo.UsersRepo;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsersService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private RolesRepo rolesRepo;

    @Transactional
    public boolean registerUser(UserRegistration users) {

       try {
           Users users1 = new Users();
           users1.setEmail(users.getEmail());
           users1.setPassword(passwordEncoder.encode(users.getPassword()));

           Set<UserRoles> userRolesSet = new HashSet<>();
           for(String roles : users.getRoles()){

               RoleType roleEnum;
               try {
                   roleEnum = RoleType.valueOf(roles);  // Converts "USER" -> RoleType.USER
               } catch (IllegalArgumentException e) {
                   System.out.println("Invalid role: " + roles);
                   continue; // skip invalid role
               }

               Optional<UserRoles> userRoles = rolesRepo.findByRoles(roleEnum);
               UserRoles userRole = userRoles.orElseGet(() -> {
                   UserRoles userRoles1 = new UserRoles();
                   userRoles1.setRoles(roleEnum);
                 return  rolesRepo.save(userRoles1);
                   }); // save if not present
                   userRolesSet.add(userRole);
                   System.out.println("User Roles are :" + userRoles);
           }

           users1.setRole(userRolesSet);
           usersRepo.save(users1);
           return true;
       } catch(Exception e){
           return false;
       }

    }


}
