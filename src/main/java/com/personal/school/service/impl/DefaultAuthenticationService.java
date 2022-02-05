package com.personal.school.service.impl;

import com.personal.school.model.User;
import com.personal.school.repository.UserRepository;
import com.personal.school.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultAuthenticationService implements AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isPresent()) {
            return user.get();
        }

        throw new UsernameNotFoundException("Invalid Fields");
    }

}
