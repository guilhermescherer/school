package com.personal.school.config.handler.http;

import com.personal.school.dto.error.ExceptionErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@ControllerAdvice
public class BadRequestHandler {

    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ExceptionErrorDTO handleError(Exception ex){
        ExceptionErrorDTO exceptionError = new ExceptionErrorDTO();

        exceptionError.setCode(HttpStatus.NOT_FOUND);
        exceptionError.setDate(LocalDateTime.now());
        exceptionError.setMessage(ex.getMessage());

        return exceptionError;
    }

}
