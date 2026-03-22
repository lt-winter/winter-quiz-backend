package com.winter.quiz.service.user;

import com.winter.quiz.entity.User;

public interface UserService {
    User createUser(User user);

    Boolean hasUserWithEmail(String email);
}
