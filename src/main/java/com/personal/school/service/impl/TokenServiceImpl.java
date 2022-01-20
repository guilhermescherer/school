package com.personal.school.service.impl;

import com.personal.school.model.User;
import com.personal.school.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${school.jwt.expiration}")
    private String expiration;

    @Value("${school.jwt.secret}")
    private String secret;

    @Override
    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Date today = new Date();
        Date expirationDate = new Date(today.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("School Manager API")
                .setSubject(user.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    @Override
    public Long isValidToken(String token) {
        try {
            Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
            return Long.parseLong(body.getSubject());
        }
        catch (Exception e){
            return null;
        }
    }
}
