package com.ms.quizapp.service;

import com.ms.quizapp.model.QsnAnswers;
import com.ms.quizapp.repo.QsnAnsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QsnAnsService {

    @Autowired
private QsnAnsRepo qsnRepo;

    public List<QsnAnswers> getAllQuestions() {
       return qsnRepo.findAll();
    }

    public List<QsnAnswers> findByCategory(String category) {
        return qsnRepo.findByCategory(category);
    }

    public void addQuestions(QsnAnswers qs) {

        qsnRepo.save(qs);
    }

    public boolean updateQuestion(QsnAnswers qs, String sno) {
        Optional<QsnAnswers> optQs = qsnRepo.findById(qs.getSno());
        if(optQs.isPresent()){
            QsnAnswers exQsn = optQs.get();
            exQsn.setQuestions(qs.getQuestions());
            exQsn.setCategory(qs.getCategory());
            exQsn.setOp1(qs.getOp1());
            exQsn.setOp2(qs.getOp2());
            exQsn.setOp3(qs.getOp3());
            exQsn.setOp4(qs.getOp4());
            exQsn.setRightAnswer(qs.getRightAnswer());

        qsnRepo.save(exQsn);
        return true;
        } else{
            return false;
        }


    }
}
