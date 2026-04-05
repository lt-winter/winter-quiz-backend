package com.winter.quiz.service.quiz;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.quiz.dto.QuestionDTO;
import com.winter.quiz.dto.QuizDTO;
import com.winter.quiz.entity.Question;
import com.winter.quiz.entity.Quiz;
import com.winter.quiz.repository.QuestionRepository;
import com.winter.quiz.repository.QuizRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public QuizDTO createQuiz(QuizDTO dto) {
        Quiz quiz = new Quiz();

        quiz.setTitle(dto.getTitle());
        quiz.setDescription(dto.getDescription());
        quiz.setTime(dto.getTime());

        return quizRepository.save(quiz).getDto();
    }

    public QuestionDTO addQuestionToQuiz(QuestionDTO dto) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(dto.getId());

        if (optionalQuiz.isPresent()) {
            Question question = new Question();

            question.setQuiz(optionalQuiz.get());
            question.setQuestionText(dto.getQuestionText());
            question.setOptionA(dto.getOptionA());
            question.setOptionB(dto.getOptionB());
            question.setOptionC(dto.getOptionC());
            question.setOptionD(dto.getOptionD());
            question.setCorrectOption(dto.getCorrectOption());

            return questionRepository.save(question).getDTO();

        }
        throw new EntityNotFoundException("Quiz not found!");
    }
}
