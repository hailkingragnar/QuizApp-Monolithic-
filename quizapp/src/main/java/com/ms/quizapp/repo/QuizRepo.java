package com.ms.quizapp.repo;

import com.ms.quizapp.model.QsnAnswers;
import com.ms.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizRepo extends JpaRepository<Quiz,String>    {

    @Query(value = "SELECT * FROM qsn_answers WHERE category = :category ORDER BY RANDOM() LIMIT :numQ" , nativeQuery = true)
    List<QsnAnswers> findQsnBy(String category, int numQ);
}
