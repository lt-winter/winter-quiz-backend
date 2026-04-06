package com.winter.quiz.service.quiz;

import java.util.List;

import com.winter.quiz.dto.QuestionDTO;
import com.winter.quiz.dto.QuizDTO;

public interface QuizService {
    QuizDTO createQuiz(QuizDTO dto);

    QuestionDTO addQuestionInQuiz(QuestionDTO dto);

    List<QuizDTO> getAllQuizzes();
}
