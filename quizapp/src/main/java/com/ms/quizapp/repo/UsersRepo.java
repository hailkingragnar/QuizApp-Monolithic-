package com.ms.quizapp.repo;

import com.ms.quizapp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<Users,String> {

    Optional<Users> findByEmail(String s);
}
