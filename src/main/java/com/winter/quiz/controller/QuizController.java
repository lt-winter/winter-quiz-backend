package com.winter.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winter.quiz.dto.QuestionDTO;
import com.winter.quiz.dto.QuizDTO;
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
}
