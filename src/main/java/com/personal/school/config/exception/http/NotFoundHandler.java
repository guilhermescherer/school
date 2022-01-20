package com.personal.school.config.exception.http;

import com.personal.school.dto.error.ExceptionErrorDTO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class NotFoundHandler {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ExceptionErrorDTO handleError(Exception ex){
        ExceptionErrorDTO exceptionError = new ExceptionErrorDTO();

        exceptionError.setCode(HttpStatus.NOT_FOUND.value());
        exceptionError.setDate(LocalDateTime.now());
        exceptionError.setMessage(ex.getMessage());

        return exceptionError;
    }

}
