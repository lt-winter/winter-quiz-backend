package com.winter.quiz.service.quiz;

import java.util.List;

import com.winter.quiz.dto.QuestionDTO;
import com.winter.quiz.dto.QuizDTO;
import com.winter.quiz.dto.QuizDetailsDTO;
import com.winter.quiz.dto.QuizResultDTO;
import com.winter.quiz.dto.SubmitQuizDTO;

public interface QuizService {
    QuizDTO createQuiz(QuizDTO dto);

    QuestionDTO addQuestionInQuiz(QuestionDTO dto);

    List<QuizDTO> getAllQuizzes();

    QuizDetailsDTO getAllQuestionsByQuiz(String quizId);

    QuizResultDTO submitQuiz(SubmitQuizDTO request);

    List<QuizResultDTO> getAllQuizResults();

    List<QuizResultDTO> getQuizResultsById(String quizId);
}
