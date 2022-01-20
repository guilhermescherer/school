package com.personal.school.service;

import com.personal.school.form.UserForm;
import com.personal.school.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getById(Long id);

    User toUser(UserForm userForm);

    void save(User user);

    List<User> getAll();
}
