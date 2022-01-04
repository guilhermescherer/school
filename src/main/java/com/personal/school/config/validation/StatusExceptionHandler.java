package com.personal.school.config.validation;

import com.personal.school.dto.error.ArgumentNotValidDTO;
import com.personal.school.dto.error.StatusExceptionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = "com.personal.school.controller")
public class StatusExceptionHandler {

    @ResponseBody
    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<StatusExceptionDTO> onException(HttpStatusCodeException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatusCode()).body(new StatusExceptionDTO(e.getRawStatusCode(), e.getStatusText()));
    }

}
