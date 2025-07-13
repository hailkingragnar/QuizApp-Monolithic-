package com.ms.quizapp.repo;

import com.ms.quizapp.enumpackage.RoleType;
import com.ms.quizapp.model.UserRoles;
import org.aspectj.weaver.loadtime.Options;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepo extends JpaRepository<UserRoles,String> {

     Optional<UserRoles> findByRoles(RoleType role);


}
