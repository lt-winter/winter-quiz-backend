package com.winter.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winter.quiz.dto.QuestionDTO;
import com.winter.quiz.dto.QuizDTO;
import com.winter.quiz.dto.SubmitQuizDTO;
import com.winter.quiz.service.quiz.QuizService;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin("*")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping()
    public ResponseEntity<?> createQuiz(@RequestBody QuizDTO dto) {
        try {
            return new ResponseEntity<>(quizService.createQuiz(dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/question")
    public ResponseEntity<?> addQuestionInQuiz(@RequestBody QuestionDTO dto) {
        try {
            return new ResponseEntity<>(quizService.addQuestionInQuiz(dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllQuizzes() {
        try {
            return new ResponseEntity<>(quizService.getAllQuizzes(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<?> getAllQuestionsByQuiz(@PathVariable String quizId) {
        try {
            return new ResponseEntity<>(quizService.getAllQuestionsByQuiz(quizId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitQuiz(@RequestBody SubmitQuizDTO dto) {
        try {
            return new ResponseEntity<>(quizService.submitQuiz(dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/results")
    public ResponseEntity<?> getAllQuizResult() {
        try {
            return new ResponseEntity<>(quizService.getAllQuizResults(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/results/{quizId}")
    public ResponseEntity<?> getQuizResultsById(@PathVariable String quizId) {
        try {
            return new ResponseEntity<>(quizService.getQuizResultsById(quizId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
