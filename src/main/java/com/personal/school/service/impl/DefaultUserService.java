package com.personal.school.service.impl;

import com.personal.school.form.UserForm;
import com.personal.school.model.Role;
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
    RoleService roleService;

    @Autowired
    UserRepository userRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User toUser(UserForm userForm) {
        List<Role> roles = roleService.getAllByIdThrow(userForm.getRoles());
        return new User(userForm.getName(), userForm.getEmail(), userForm.getPassword(), roles);
    }

    @Override
    public void save(User user) {
        user.setPassword(encodePassword(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(Long id, UserForm userForm) {
        Optional<User> optinalUser = getById(id);

        if(optinalUser.isPresent()) {

            List<Role> roles = roleService.getAllByIdThrow(userForm.getRoles());
            String password = encodePassword(userForm.getPassword());

            User user = optinalUser.get();
            user.setEmail(userForm.getEmail());
            user.setName(userForm.getName());
            user.setPassword(password);
            user.setRoles(roles);

            return user;
        } else {
            throw new EmptyResultDataAccessException("Not found user", toIntExact(id));
        }
    }
}
