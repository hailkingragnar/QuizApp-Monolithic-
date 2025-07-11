package com.ms.quizapp.dto;

import lombok.Data;

@Data
public class QuizQsn {
    private String sno;
    private String category;
    private String questions;
    private String op1;
    private String op2;
    private String op3;
    private String op4;

    public QuizQsn(String sno, String category, String questions, String op1, String op2, String op3, String op4) {
        this.sno = sno;
        this.category = category;
        this.questions = questions;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
    }

    public QuizQsn() {
    }
}
