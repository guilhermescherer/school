package com.personal.school.dto.error;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ExceptionErrorDTO {

    private Integer code;
    private LocalDateTime date;
    private String message;
    private List<ErrorDTO> errors;

}
