package com.personal.school.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityUtils {

    public static String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

}
