package com.winter.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winter.quiz.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
