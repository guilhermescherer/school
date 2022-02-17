package com.personal.school.facade;

import com.personal.school.form.UserForm;
import com.personal.school.model.User;

import java.util.List;

public interface UserFacade {

    List<User> getAll();

    User getById(Long id);

    User save(UserForm userForm);

    void remove(Long id);

    User update(Long id, UserForm userForm);
}
