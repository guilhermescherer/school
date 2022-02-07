package com.personal.school.converter;

import com.personal.school.form.UserForm;
import com.personal.school.model.User;

public interface UserConverter {

    User toUser(UserForm userForm);

    User toUser(User user, UserForm userForm);
}
