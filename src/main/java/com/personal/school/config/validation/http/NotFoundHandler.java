package com.personal.school.config.validation.http;

import com.personal.school.dto.error.ExceptionErrorDTO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class NotFoundHandler {

    @ResponseBody
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ExceptionErrorDTO handleError(Exception ex){
        ExceptionErrorDTO exceptionError = new ExceptionErrorDTO();

        exceptionError.setCode(HttpStatus.NOT_FOUND);
        exceptionError.setDate(LocalDateTime.now());
        exceptionError.setMessage(ex.getMessage());

        return exceptionError;
    }

}
