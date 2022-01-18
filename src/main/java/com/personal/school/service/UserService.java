package com.personal.school.service;

import com.personal.school.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getById(Long id);

}
