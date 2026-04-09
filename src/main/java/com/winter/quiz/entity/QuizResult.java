package com.winter.quiz.entity;

import com.winter.quiz.dto.QuizResultDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class QuizResult {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private int totalQuestions;

    private int correctAnswers;

    private double percentage;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public QuizResultDTO getDto() {
        QuizResultDTO dto = new QuizResultDTO();

        dto.setId(this.id);
        dto.setTotalQuestions(this.totalQuestions);
        dto.setCorrectAnswers(this.correctAnswers);
        dto.setPercentage(this.percentage);
        dto.setQuizName(this.quiz.getTitle());
        dto.setUsername(this.user.getName());

        return dto;
    }
}
