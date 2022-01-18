package com.personal.school.service;

import org.springframework.security.core.Authentication;

public interface TokenService {

    String generateToken(Authentication authentication);

    boolean isValidToken(String token);

    Long getUserId(String token);

}
