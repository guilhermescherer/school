package com.personal.school.service;

import com.personal.school.form.UserForm;
import com.personal.school.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll();

    User getByIdThrow(Long id);

    Optional<User> getById(Long id);

    User save(UserForm userForm);

    void remove(Long id);

    User update(Long id, UserForm userForm);
}
