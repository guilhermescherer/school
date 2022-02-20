package com.personal.school.validator.impl;

import com.personal.school.validator.NotEmpty;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DefaultNotEmpty implements ConstraintValidator<NotEmpty, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s == null || !s.isEmpty();
    }
}
