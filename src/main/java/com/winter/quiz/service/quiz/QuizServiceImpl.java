package com.winter.quiz.service.quiz;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.quiz.dto.QuestionDTO;
import com.winter.quiz.dto.QuizDTO;
import com.winter.quiz.dto.QuizDetailsDTO;
import com.winter.quiz.dto.QuizResultDTO;
import com.winter.quiz.dto.SubmitQuizDTO;
import com.winter.quiz.dto.response.QuestionResponse;
import com.winter.quiz.entity.Question;
import com.winter.quiz.entity.Quiz;
import com.winter.quiz.entity.QuizResult;
import com.winter.quiz.entity.User;
import com.winter.quiz.repository.QuestionRepository;
import com.winter.quiz.repository.QuizRepository;
import com.winter.quiz.repository.QuizResultRepository;
import com.winter.quiz.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizResultRepository quizResultRepository;

    @Autowired
    private UserRepository userRepository;

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

    public QuizResultDTO submitQuiz(SubmitQuizDTO request) {
        Quiz quiz = quizRepository
                .findById(request.getQuizId()).orElseThrow(() -> new EntityNotFoundException("Quiz not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        int correctAnswers = 0;

        for (QuestionResponse response : request.getResponses()) {
            Question question = questionRepository.findById(response.getQuestionId())
                    .orElseThrow(() -> new EntityNotFoundException("Question not found"));

            if (isCorrectAnswer(question, response.getSelectedOption())) {
                correctAnswers++;
            }
        }

        int totalQuestions = quiz.getQuestions().size();
        double percentage = ((double) correctAnswers / totalQuestions) * 100;

        QuizResult quizResult = new QuizResult();

        quizResult.setQuiz(quiz);
        quizResult.setUser(user);
        quizResult.setTotalQuestions(totalQuestions);
        quizResult.setCorrectAnswers(correctAnswers);
        quizResult.setPercentage(percentage);

        return quizResultRepository.save(quizResult).getDto();
    }

    public List<QuizResultDTO> getAllQuizResults() {
        return quizResultRepository.findAll().stream().map(QuizResult::getDto).collect(Collectors.toList());
    }

    public List<QuizResultDTO> getQuizResultsByQuizId(String quizId) {
        return quizResultRepository.findByQuizId(quizId).stream().map(QuizResult::getDto).collect(Collectors.toList());
    }

    public List<QuizResultDTO> getQuizResultsByUserId(String userId) {
        return quizResultRepository.findByUserId(userId).stream().map(QuizResult::getDto).collect(Collectors.toList());
    }

    // String formatting method
    private boolean isCorrectAnswer(Question question, String selectedOption) {
        String expected = normalizeOption(question, question.getCorrectOption());
        String selected = normalizeOption(question, selectedOption);

        return expected != null && expected.equals(selected);
    }

    private String normalizeOption(Question question, String optionValue) {
        if (optionValue == null) {
            return null;
        }

        String trimmed = optionValue.trim();
        if (trimmed.isEmpty()) {
            return null;
        }

        String compact = trimmed.replace(" ", "").replace("_", "").toUpperCase(Locale.ROOT);

        if (compact.equals("A") || compact.equals("OPTIONA")) {
            return "A";
        }
        if (compact.equals("B") || compact.equals("OPTIONB")) {
            return "B";
        }
        if (compact.equals("C") || compact.equals("OPTIONC")) {
            return "C";
        }
        if (compact.equals("D") || compact.equals("OPTIOND")) {
            return "D";
        }

        String normalizedText = canonicalText(trimmed);

        if (question.getOptionA() != null && canonicalText(question.getOptionA()).equals(normalizedText)) {
            return "A";
        }
        if (question.getOptionB() != null && canonicalText(question.getOptionB()).equals(normalizedText)) {
            return "B";
        }
        if (question.getOptionC() != null && canonicalText(question.getOptionC()).equals(normalizedText)) {
            return "C";
        }
        if (question.getOptionD() != null && canonicalText(question.getOptionD()).equals(normalizedText)) {
            return "D";
        }

        return trimmed.toUpperCase(Locale.ROOT);
    }

    private String canonicalText(String value) {
        return value == null
                ? ""
                : value.trim().replaceAll("[^a-zA-Z0-9]", "").toUpperCase(Locale.ROOT);
    }
}
