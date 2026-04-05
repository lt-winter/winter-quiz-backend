package com.winter.quiz.entity;

import java.util.List;

import com.winter.quiz.dto.QuizDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;

    private String description;

    // If quiz has no limit time, it has value -1;
    // TODO: Each question has its own limit time
    private Long time;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Question> questions;

    public QuizDTO getDto() {
        QuizDTO quizDTO = new QuizDTO();

        quizDTO.setId(id);
        quizDTO.setTitle(title);
        quizDTO.setDescription(description);
        quizDTO.setTime(time);

        return quizDTO;
    }
}
