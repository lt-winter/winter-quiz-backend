package com.winter.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winter.quiz.entity.QuizResult;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, String> {

}
