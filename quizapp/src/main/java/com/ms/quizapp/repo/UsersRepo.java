package com.ms.quizapp.repo;

import com.ms.quizapp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users,String> {
}
