package com.ms.quizapp.controller;

import com.ms.quizapp.dto.QuizQsn;
import com.ms.quizapp.dto.UserResponse;
import com.ms.quizapp.service.QuizService;
import com.ms.quizapp.wrapper.ApiWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
     @Autowired
    private QuizService quizService;
  @RequestMapping("/createquiz")
    public ResponseEntity<ApiWrapper<Void>> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
       boolean stat =  quizService.createQuiz(category,numQ,title);
      if(stat){
          return ResponseEntity.ok(new ApiWrapper<>("Success",true));
      } else {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiWrapper<>("Success",true));
      }
  }
  @GetMapping("/getquiz/{id}")
  public ResponseEntity<ApiWrapper<List<QuizQsn>>> getQuiz(@PathVariable String id){
      System.out.println("Inside controller :" + id);
     List<QuizQsn> quizQsn = quizService.getQuiz(id);
      return ResponseEntity.ok(new ApiWrapper<>(quizQsn,"Success",true));
  }
  @PostMapping("/getresult/{id}")
    public ResponseEntity<ApiWrapper<Integer>> getResult(@PathVariable String id, @RequestBody List<UserResponse> userResponse){
      int score = quizService.getResult(userResponse,id);
      return ResponseEntity.ok(new ApiWrapper<>(score,"Success", true));
  }



}
