package com.winter.quiz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winter.quiz.entity.User;
import com.winter.quiz.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByRole(UserRole role);

    User findFirstByEmail(String email);

    Optional<User> findByEmail(String email);
}
