package com.personal.school.service.impl;

import com.personal.school.converter.UserConverter;
import com.personal.school.form.UserForm;
import com.personal.school.model.Role;
import com.personal.school.model.Teacher;
import com.personal.school.model.User;
import com.personal.school.repository.UserRepository;
import com.personal.school.service.RoleService;
import com.personal.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.personal.school.utils.SecurityUtils.encodePassword;
import static java.lang.Math.toIntExact;

@Service
public class DefaultUserService implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserConverter userConverter;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getByIdThrow(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new EmptyResultDataAccessException("Not found user", toIntExact(id));
        }
        return user.get();
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(UserForm userForm) {
        User user = userConverter.toUser(userForm);
        userRepository.save(user);
        return user;
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(Long id, UserForm userForm) {
        User user = getByIdThrow(id);
        return userConverter.toUser(user, userForm);
    }
}
