package com.personal.school.controller;

import com.personal.school.dto.TokenDTO;
import com.personal.school.form.LoginForm;
import com.personal.school.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> auth(@RequestBody @Valid LoginForm loginForm) {
        UsernamePasswordAuthenticationToken fields = new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword());

        try {
            Authentication authentication = authManager.authenticate(fields);
            String token = tokenService.generateToken(authentication);

            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        } catch (AuthenticationException ex){
            ex.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
