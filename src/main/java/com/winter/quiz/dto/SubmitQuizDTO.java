package com.winter.quiz.dto;

import java.util.List;

import com.winter.quiz.dto.response.QuestionResponse;

import lombok.Data;

@Data
public class SubmitQuizDTO {

    private String quizId;

    private String userId;

    private List<QuestionResponse> responses;
}
