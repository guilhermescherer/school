package com.personal.school.dto.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusExceptionDTO {

    private int code;
    private String message;

    public StatusExceptionDTO(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
