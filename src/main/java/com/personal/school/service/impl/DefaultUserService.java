package com.personal.school.service.impl;

import com.personal.school.exception.NotFoundException;
import com.personal.school.model.User;
import com.personal.school.repository.UserRepository;
import com.personal.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        final List<User> users = userRepository.findAll();
        if(isEmpty(users)) {
            throw new NotFoundException("Not found any user");
        }
        return users;
    }

    @Override
    public User getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new NotFoundException("Not found user");
        }
        return user.get();
    }

    @Override
    public User save(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public void remove(User user) {
        userRepository.delete(user);
    }
}
