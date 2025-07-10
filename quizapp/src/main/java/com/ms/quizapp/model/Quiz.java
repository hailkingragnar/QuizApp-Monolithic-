package com.ms.quizapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Quiz {
    @Id
    private String id = UUID.randomUUID().toString();
    private String Category;
    private String title;
    @ManyToMany
    private List<QsnAnswers> qsnAnswersList;
}
