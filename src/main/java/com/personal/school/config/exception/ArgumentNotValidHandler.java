package com.personal.school.config.exception;

import com.personal.school.dto.error.ErrorDTO;
import com.personal.school.dto.error.ExceptionErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ArgumentNotValidHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionErrorDTO handleError(MethodArgumentNotValidException ex){
        ExceptionErrorDTO exceptionError = new ExceptionErrorDTO();
        exceptionError.setCode(HttpStatus.PRECONDITION_FAILED.value());
        exceptionError.setDate(LocalDateTime.now());
        exceptionError.setMessage(ex.getMessage());

        List<ErrorDTO> errors = new ArrayList<>();

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            errors.add(new ErrorDTO(e.getField(), message));
        });

        exceptionError.setErrors(errors);

        return exceptionError;
    }


}
