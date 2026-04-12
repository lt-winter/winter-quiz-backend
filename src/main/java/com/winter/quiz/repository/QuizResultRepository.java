package com.winter.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winter.quiz.entity.QuizResult;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, String> {
    List<QuizResult> findByQuizId(String quizId);

    List<QuizResult> findByUserId(String userId);

}
