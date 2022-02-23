package com.personal.school.facade.impl;

import com.personal.school.converter.Converter;
import com.personal.school.converter.impl.UserConverter;
import com.personal.school.facade.Facade;
import com.personal.school.facade.UserFacade;
import com.personal.school.form.UserForm;
import com.personal.school.model.User;
import com.personal.school.service.RoleService;
import com.personal.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Facade
public class DefaultUserFacade implements UserFacade {

    private final UserService userService;
    private final RoleService roleService;
    private final Converter<UserForm, User> userConverter;

    @Autowired
    public DefaultUserFacade(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
        this.userConverter = new UserConverter(roleService);
    }

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
        final User user = userConverter.convert(userForm);
        return userService.save(user);
    }

    @Override
    public void remove(Long id) {
        final User user = userService.getById(id);
        userService.remove(user);
    }

    @Override
    public User update(Long id, UserForm userForm) {
        User user = userService.getById(id);
        return userConverter.convert(user, userForm);
    }
}
