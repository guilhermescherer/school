package com.personal.school.converter.impl;

import com.personal.school.converter.ConvertMethod;
import com.personal.school.converter.UserConverter;
import com.personal.school.form.UserForm;
import com.personal.school.model.User;
import com.personal.school.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.personal.school.converter.ConvertMethod.ADD;
import static com.personal.school.converter.ConvertMethod.UPDATE;
import static com.personal.school.utils.ConverterUtils.isValidSet;
import static com.personal.school.utils.SecurityUtils.encodePassword;

@Component
public class DefaultUserConverter implements UserConverter {

    @Autowired
    RoleService roleService;

    @Override
    public User toUser(UserForm userForm) {
        User user = new User();

        populateEmail(user, userForm.getEmail(), ADD);
        populateName(user, userForm.getName(), ADD);
        populatePassword(user, userForm.getPassword(), ADD);
        populateRoles(user, userForm.getRoles(), ADD);

        return user;
    }

    @Override
    public User toUser(User user, UserForm userForm) {
        populateEmail(user, userForm.getEmail(), UPDATE);
        populateName(user, userForm.getName(), UPDATE);
        populatePassword(user, userForm.getPassword(), UPDATE);
        populateRoles(user, userForm.getRoles(), UPDATE);

        return user;
    }

    private void populateRoles(User user, List<Long> roles, ConvertMethod convertMethod) {
        if(isValidSet(roles, convertMethod)) {
            user.setRoles(roleService.getAllById(roles));
        }
    }

    private void populatePassword(User user, String password, ConvertMethod convertMethod) {
        if(isValidSet(password, convertMethod)) {
            user.setPassword(encodePassword(password));
        }
    }

    private void populateName(User user, String name, ConvertMethod convertMethod) {
        if(isValidSet(name, convertMethod)) {
            user.setName(name);
        }
    }

    private void populateEmail(User user, String email, ConvertMethod convertMethod) {
        if(isValidSet(email, convertMethod)) {
            user.setEmail(email);
        }
    }

}
