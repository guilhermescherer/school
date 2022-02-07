package com.personal.school.converter.impl;

import com.personal.school.converter.UserConverter;
import com.personal.school.form.UserForm;
import com.personal.school.model.Role;
import com.personal.school.model.User;
import com.personal.school.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.personal.school.utils.SecurityUtils.encodePassword;

@Component
public class DefaultUserConverter implements UserConverter {

    @Autowired
    RoleService roleService;

    @Override
    public User toUser(UserForm userForm) {
        return toUser(false, null, userForm);
    }

    @Override
    public User toUser(User user, UserForm userForm) {
        return toUser(true, user, userForm);
    }

    private User toUser(Boolean isUpdate, User user, UserForm userForm) {
        List<Role> roles = roleService.getAllByIdThrow(userForm.getRoles());
        String password = encodePassword(userForm.getPassword());

        if(isUpdate) {
            user.setEmail(userForm.getEmail());
            user.setName(userForm.getName());
            user.setPassword(password);
            user.setRoles(roles);
        } else {
            user = new User(userForm.getName(), userForm.getEmail(), password, roles);
        }

        return user;
    }

}
