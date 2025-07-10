package com.ms.quizapp.repo;

import com.ms.quizapp.model.QsnAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QsnAnsRepo extends JpaRepository<QsnAnswers, String> {

    @Query("Select q from QsnAnswers q where q.category = :category")   //not reqd to use query as category is the same column we can directly by findByCategory(String category)
    List<QsnAnswers> findByCategory(@Param("category") String category);
}
