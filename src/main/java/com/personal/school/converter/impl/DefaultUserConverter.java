package com.personal.school.converter.impl;

import com.personal.school.converter.ConvertMethod;
import com.personal.school.converter.UserConverter;
import com.personal.school.form.UserForm;
import com.personal.school.model.User;
import com.personal.school.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.personal.school.converter.ConvertMethod.ADD;
import static com.personal.school.converter.ConvertMethod.UPDATE;
import static com.personal.school.utils.ConverterUtils.isValidSet;
import static com.personal.school.utils.PropertyUtils.getNullProperties;
import static com.personal.school.utils.SecurityUtils.encodePassword;

@Component
public class DefaultUserConverter implements UserConverter {

    @Autowired
    RoleService roleService;

    @Override
    public User toUser(UserForm form) {
        User user = new User();
        BeanUtils.copyProperties(form, user);

        populatePassword(user, form.getPassword(), ADD);
        populateRoles(user, form.getRoles(), ADD);

        return user;
    }

    @Override
    public User toUser(User user, UserForm form) {
        BeanUtils.copyProperties(form, user, getNullProperties(form));

        populatePassword(user, form.getPassword(), UPDATE);
        populateRoles(user, form.getRoles(), UPDATE);

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
}
