package com.personal.school.converter.impl;

import com.personal.school.enums.ConvertMethod;
import com.personal.school.converter.UserConverter;
import com.personal.school.form.UserForm;
import com.personal.school.model.User;
import com.personal.school.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.personal.school.enums.ConvertMethod.ADD;
import static com.personal.school.enums.ConvertMethod.UPDATE;
import static com.personal.school.utils.ConverterUtils.isValidSet;
import static com.personal.school.utils.PropertyUtils.getNullProperties;
import static com.personal.school.utils.SecurityUtils.encodePassword;

@Component
public class DefaultUserConverter implements UserConverter {

    @Autowired
    RoleService roleService;

    @Override
    public User toUser(UserForm source) {
        User target = new User();
        BeanUtils.copyProperties(source, target);

        populatePassword(target, source.getPassword(), ADD);
        populateRoles(target, source.getRoles(), ADD);

        return target;
    }

    @Override
    public User toUser(User target, UserForm source) {
        BeanUtils.copyProperties(source, target, getNullProperties(source));

        populatePassword(target, source.getPassword(), UPDATE);
        populateRoles(target, source.getRoles(), UPDATE);

        return target;
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
