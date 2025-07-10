package com.ms.quizapp.controller;

import com.ms.quizapp.model.QsnAnswers;
import com.ms.quizapp.service.QsnAnsService;
import com.ms.quizapp.wrapper.ApiWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")

public class QsnController {

    @Autowired
   private QsnAnsService qsn;



        @GetMapping("/allquestions")
        public ResponseEntity<ApiWrapper<List<QsnAnswers>>> questions(){
        try {
            List<QsnAnswers> qs = qsn.getAllQuestions();
            if (qs.isEmpty()){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiWrapper<>("No Content",false));
            } else {
                return ResponseEntity.ok(new ApiWrapper<>(qs,"Success",true));

            }
        }catch(Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiWrapper<>("failed",false));

        }
        }

        @GetMapping("/category/{category}")
         public List<QsnAnswers> getQuestionsByCategory(@PathVariable String category){
           return qsn.findByCategory(category);
         }

        @PostMapping("/addquestions")
         public ResponseEntity<ApiWrapper<Void>> addQuestions(@RequestBody QsnAnswers qs){
         qsn.addQuestions(qs);
         return ResponseEntity.status(HttpStatus.CREATED)
                 .body(new ApiWrapper<>("Successfull",true));

         }

         @PutMapping("/updateqsn/{sno}")
         public ResponseEntity<ApiWrapper<Void>> updateQsn(@PathVariable String sno, @RequestBody QsnAnswers qs){
        boolean success =  qsn.updateQuestion(qs,sno);
            if(success){
                return ResponseEntity.ok(new ApiWrapper<>("Success",true));
            } else {
                return ResponseEntity.ok(new ApiWrapper<>("Failed",false));

            }
         }




}
