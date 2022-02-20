package com.personal.school.facade.impl;

import com.personal.school.facade.Facade;
import com.personal.school.facade.UserFacade;
import com.personal.school.form.UserForm;
import com.personal.school.model.User;
import com.personal.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Facade
public class DefaultUserFacade implements UserFacade {

    @Autowired
    UserService userService;

    @Override
    public List<User> getAll() {
        return userService.getAll();
    }

    @Override
    public User getById(Long id) {
        return userService.getById(id);
    }

    @Override
    public User save(UserForm userForm) {
        return userService.save(userForm);
    }

    @Override
    public void remove(Long id) {
        final User user = userService.getById(id);
        userService.remove(user);
    }

    @Override
    public User update(Long id, UserForm userForm) {
        User user = userService.getById(id);
        return userService.update(user, userForm);
    }
}
