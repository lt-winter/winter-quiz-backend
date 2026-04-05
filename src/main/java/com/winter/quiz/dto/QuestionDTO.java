package com.winter.quiz.dto;

import lombok.Data;

@Data
public class QuestionDTO {
    private String id;

    private String questionText;

    // TODO: Replace it by Option entity later
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    private String correctOption;
}
