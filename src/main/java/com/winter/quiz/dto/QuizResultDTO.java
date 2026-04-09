package com.winter.quiz.dto;

import lombok.Data;

@Data
public class QuizResultDTO {
    private String id;

    private int totalQuestions;

    private int correctAnswers;

    private double percentage;

    private String quizName;
    private String username;

}
