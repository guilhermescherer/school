package com.personal.school.validation.impl;

import com.personal.school.validation.CEP;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CEPValidator implements ConstraintValidator<CEP, String> {

    @Override
    public boolean isValid(String cep, ConstraintValidatorContext constraintValidatorContext) {
        return true;
    }
}
