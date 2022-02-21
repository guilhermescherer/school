package com.personal.school.service;

import com.personal.school.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getById(Long id);

    User save(User user);

    void remove(User user);
}
