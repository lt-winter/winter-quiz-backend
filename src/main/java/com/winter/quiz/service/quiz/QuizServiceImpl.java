package com.winter.quiz.service.quiz;

import org.springframework.beans.factory.annotation.Autowired;

import com.winter.quiz.dto.QuizDTO;
import com.winter.quiz.entity.Quiz;
import com.winter.quiz.repository.QuizRepository;

public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepository quizRepository;

    public QuizDTO createQuiz(QuizDTO dto) {
        Quiz quiz = new Quiz();

        quiz.setTitle(dto.getTitle());
        quiz.setDescription(dto.getDescription());
        quiz.setTime(dto.getTime());

        return quizRepository.save(quiz).getDto();
    }
}
