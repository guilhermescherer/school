package com.personal.school.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorFormDto {

    private String field;
    private String error;

    public ErrorFormDto(String field, String error) {
        this.field = field;
        this.error = error;
    }
}
