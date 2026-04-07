package com.winter.quiz.service.quiz;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.quiz.dto.QuestionDTO;
import com.winter.quiz.dto.QuizDTO;
import com.winter.quiz.dto.QuizDetailsDTO;
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

    public QuestionDTO addQuestionInQuiz(QuestionDTO dto) {
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

    public List<QuizDTO> getAllQuizzes() {
        return quizRepository.findAll().stream()
                .peek(quiz -> quiz.setTime(quiz.getQuestions().size() * quiz.getTime())).collect(Collectors.toList())
                .stream().map(Quiz::getDto).collect(Collectors.toList());
    }

    public QuizDetailsDTO getAllQuestionsByQuiz(String quizId) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);

        QuizDetailsDTO quizDetailsDTO = new QuizDetailsDTO();

        if (optionalQuiz.isPresent()) {
            QuizDTO quizDTO = optionalQuiz.get().getDto();
            quizDTO.setTime(optionalQuiz.get().getTime() * optionalQuiz.get().getQuestions().size());
            quizDetailsDTO.setQuizDTO(quizDTO);
            quizDetailsDTO.setQuestions(
                    optionalQuiz.get().getQuestions().stream().map(Question::getDTO).collect(Collectors.toList()));
        }
        return quizDetailsDTO;
    }
}
