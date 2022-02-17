package com.personal.school.service;

import com.personal.school.form.UserForm;
import com.personal.school.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getById(Long id);

    User save(UserForm userForm);

    void remove(User user);

    User update(User id, UserForm userForm);
}
