package com.personal.school.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityUtils {

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public static String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

}
