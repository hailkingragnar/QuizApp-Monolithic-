package com.ms.quizapp.service;

import com.ms.quizapp.dao.QuizQsn;
import com.ms.quizapp.dao.UserResponse;
import com.ms.quizapp.model.QsnAnswers;
import com.ms.quizapp.model.Quiz;
import com.ms.quizapp.repo.QsnAnsRepo;
import com.ms.quizapp.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;
    @Autowired
    private QsnAnsRepo qsnAnsRepo;

    int score = 0;

    public boolean createQuiz(String category, int numQ, String title) {
        try {
            List<QsnAnswers> quizQsn = quizRepo.findQsnBy(category, numQ);
            Quiz quiz = new Quiz();
            quiz.setCategory(category);
            quiz.setTitle(title);
            quiz.setQsnAnswersList(quizQsn);
            quizRepo.save(quiz);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }


    public List<QuizQsn> getQuiz(String id) {
        Optional<Quiz> quiz = quizRepo.findById(id);
        List<QuizQsn> quizQsns = new ArrayList<>();

        if (quiz.isPresent()) {
            for (QsnAnswers q : quiz.get().getQsnAnswersList()) {
                QuizQsn quizQsn = new QuizQsn();
                quizQsn.setSno(q.getSno());
                quizQsn.setCategory(q.getCategory());
                quizQsn.setQuestions(q.getQuestions());
                quizQsn.setOp1(q.getOp1());
                quizQsn.setOp2(q.getOp2());
                quizQsn.setOp3(q.getOp3());
                quizQsn.setOp4(q.getOp4());
                quizQsns.add(quizQsn);
            }

        }


        return quizQsns;

    }

    public int getResult(List<UserResponse> userResponse, String id) {
        int score = 0;
        for(UserResponse userResponse1 : userResponse){
            System.out.println("Id and answers are : " + userResponse1.getId() + userResponse1.getAnswer());
        }
        HashMap<String, String> CorrectAnswer = new HashMap<>();
        Optional<Quiz> quiz = quizRepo.findById(id);

        List<QsnAnswers> qsnAnswersList = null;
        if (quiz.isPresent()) {
            qsnAnswersList = quiz.get().getQsnAnswersList();
        }

        if (qsnAnswersList != null) {
            for (QsnAnswers q : qsnAnswersList) {
                CorrectAnswer.put(q.getSno(), q.getRightAnswer());
            }

            for (UserResponse ur : userResponse) {
                String correctAns = CorrectAnswer.get(ur.getId());
                if (correctAns != null && correctAns.equalsIgnoreCase(ur.getAnswer())) {
                    score++;
                }
            }

            return score;
        }

        return 0;
    }
}