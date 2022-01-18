package com.personal.school.service.impl;

import com.personal.school.model.User;
import com.personal.school.repository.UserRepository;
import com.personal.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }
}
