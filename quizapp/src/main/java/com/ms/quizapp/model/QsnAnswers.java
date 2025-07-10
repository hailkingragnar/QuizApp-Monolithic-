package com.ms.quizapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class QsnAnswers {
    @Id
    private String sno = UUID.randomUUID().toString();
    private String category;
    private String questions;
    private String op1;
    private String op2;
    private String op3;
    private String op4;
    private String rightAnswer;


}
