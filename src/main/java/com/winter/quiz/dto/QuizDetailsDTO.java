package com.winter.quiz.dto;

import java.util.List;

import lombok.Data;

@Data
public class QuizDetailsDTO {

    private QuizDTO quizDTO;

    private List<QuestionDTO> questions;

     
}
