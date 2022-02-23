package com.personal.school.converter.impl;

import com.personal.school.converter.Converter;
import com.personal.school.form.UserForm;
import com.personal.school.model.Role;
import com.personal.school.model.User;
import com.personal.school.service.RoleService;
import com.personal.school.utils.FormatterUtils;
import com.personal.school.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.function.Function;

import static com.personal.school.utils.SetterUtils.*;
import static com.personal.school.utils.PropertyUtils.getNullProperties;

public class UserConverter implements Converter<UserForm, User> {

    private final RoleService roleService;

    public UserConverter(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public User convert(UserForm source) {
        return convert(new User(), source);
    }

    @Override
    public User convert(User target, UserForm source) {
        BeanUtils.copyProperties(source, target, getNullProperties(source));

        final Function<String, String> password = SecurityUtils::encodePassword;
        final Function<List<Long>, List<Role>> roles = roleService::getAllById;

        setter(target::setPassword, source.getPassword(), password);
        setter(target::setRoles, source.getRoles(), roles);

        return target;
    }
}
