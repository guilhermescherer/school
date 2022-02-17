package com.personal.school.config.exception.http;

import com.personal.school.dto.error.ExceptionErrorDTO;
import com.personal.school.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class NotFoundHandler {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ExceptionErrorDTO handleError(Exception ex){
        ExceptionErrorDTO exceptionError = new ExceptionErrorDTO();

        List<String> trace = Arrays.stream(ex.getStackTrace())
                .limit(5)
                .map(StackTraceElement::toString)
                .collect(Collectors.toList());

        exceptionError.setCode(HttpStatus.NOT_FOUND.value());
        exceptionError.setDate(LocalDateTime.now());
        exceptionError.setMessage(ex.getMessage());
        exceptionError.setTrace(trace);

        return exceptionError;
    }

}
