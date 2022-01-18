package com.personal.school.service;

import org.springframework.security.core.Authentication;

public interface TokenService {

    String generateToken(Authentication authentication);

    Long isValidToken(String token);

}
