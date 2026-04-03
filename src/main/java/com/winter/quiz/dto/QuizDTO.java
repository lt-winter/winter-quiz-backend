package com.winter.quiz.dto;

import lombok.Data;

@Data
public class QuizDTO {

    private String id;

    private String title;

    private String description;

    private Long time;
}
