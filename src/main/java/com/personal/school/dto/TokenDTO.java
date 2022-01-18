package com.personal.school.dto;

public class TokenDTO {

    private String token;
    private String type;

    public TokenDTO(String token, String type) {
        this.token = token;
        this.type = type;
    }
}
